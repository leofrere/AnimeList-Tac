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

import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
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
        binding.detailSwitch.setChecked(dataRepository.isFavoriteAnime(id));
        binding.detailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                if (status){
                    dataRepository.addFavoriteAnime(anime);
                } else {
                    dataRepository.deleteFavoriteAnime(id);
                }
            }
        });
    }

    public Anime createAnime(Intent intent){
        Anime anime = new Anime(intent.getIntExtra("id",-1));
        anime.title(intent.getStringExtra("title"));
        anime.synopsis(intent.getStringExtra("synopsis"));
        anime.imgUrl(intent.getStringExtra("imageURL"));
        anime.type(intent.getStringExtra("type"));
        anime.startDate(intent.getStringExtra("startDate"));
        anime.endDate(intent.getStringExtra("endDate"));
        anime.nbEpisode(intent.getIntExtra("episode", 0));
        return anime;
    }
}