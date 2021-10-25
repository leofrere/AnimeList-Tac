package project.animelist_tac.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import project.animelist_tac.R;
import project.animelist_tac.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        binding.detailTitle.setText(intent.getStringExtra("title"));
        binding.detailSynopsis.setText(intent.getStringExtra("synopsis"));
        binding.detailType.setText(intent.getStringExtra("type"));
        binding.detailBegin.setText(intent.getStringExtra("startDate"));
        binding.detailEnd.setText(intent.getStringExtra("endDate"));
        binding.detailEpisode.setText(intent.getStringExtra("episode"));
        Picasso.with(binding.detailImage.getContext()).load(intent.getStringExtra("imageURL")).into((Target) binding.detailImage);


    }
}