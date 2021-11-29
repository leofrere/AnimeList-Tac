package project.animelist_tac.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.data.localData.Entity.AnimeEntity;
import project.animelist_tac.databinding.ActivityDetailBinding;
import project.animelist_tac.model.Anime;

public class DetailActivity extends AppCompatActivity {

    private int id;
    private DataRepository dataRepository ;
    private Anime anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        dataRepository = new DataRepository(view.getContext());
        anime = createAnime(intent);
        id = intent.getIntExtra("id",-1);
        binding.detailTitle.setText(intent.getStringExtra("title"));
        binding.detailSynopsis.setText(intent.getStringExtra("synopsis"));
        binding.detailType.setText("Type : " + intent.getStringExtra("type"));
        binding.detailBegin.setText("Start : " + intent.getStringExtra("startDate"));
        binding.detailEnd.setText("End : " + intent.getStringExtra("endDate"));
        binding.detailEpisode.setText("Episodes : " + intent.getIntExtra("episode", 0));
        Picasso.with(binding.detailImage.getContext()).load(intent.getStringExtra("imageURL")).into(binding.imageView);
        binding.leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });
        dataRepository.getFavoriteAnime(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableMaybeObserver<AnimeEntity>() {

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull AnimeEntity animeEntity) {
                        binding.detailSwitch.setChecked(true);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        binding.detailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                if (status){
                    dataRepository.getFavoriteAnime(id).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableMaybeObserver<AnimeEntity>() {

                                @Override
                                public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull AnimeEntity animeEntity) {

                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                }

                                @Override
                                public void onComplete() {
                                    dataRepository.addFavoriteAnime(anime.asAnimeEntity());
                                }
                            });
                } else {
                    dataRepository.deleteFavoriteAnime(anime.asAnimeEntity());
                }
            }
        });
    }

    public Anime createAnime(Intent intent){
        Anime anime = new Anime();
        anime.id = intent.getIntExtra("id",-1);
        anime.title = intent.getStringExtra("title");
        anime.synopsis = (intent.getStringExtra("synopsis"));
        anime.image_url = (intent.getStringExtra("imageURL"));
        anime.type = (intent.getStringExtra("type"));
        anime.start_date = (intent.getStringExtra("startDate"));
        anime.end_date = (intent.getStringExtra("endDate"));
        anime.episodes = (intent.getIntExtra("episode", 0));
        return anime;
    }
}