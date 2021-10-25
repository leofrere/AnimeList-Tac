package project.animelist_tac.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

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
        anime = (Anime) intent.getExtras().get("anime");
        id = intent.getIntExtra("id",-1);
        binding.detailTitle.setText(intent.getStringExtra("title"));
        binding.detailSynopsis.setText(intent.getStringExtra("synopsis"));
        binding.detailType.setText(intent.getStringExtra("type"));
        binding.detailBegin.setText(intent.getStringExtra("startDate"));
        binding.detailEnd.setText(intent.getStringExtra("endDate"));
        binding.detailEpisode.setText(intent.getStringExtra("episode"));
        Picasso.with(binding.detailImage.getContext()).load(intent.getStringExtra("imageURL")).into((Target) binding.detailImage);
        binding.leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
}