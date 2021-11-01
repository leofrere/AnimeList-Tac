package project.animelist_tac.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.model.Anime;
import project.animelist_tac.view.DetailActivity;
import project.animelist_tac.view.SearchFragment;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Anime> animeList;
    private DataRepository dataRepository;
    private SearchFragment fragment;

    public SearchAdapter(List<Anime> animeList, DataRepository dataRepository, SearchFragment fragment){
        this.animeList = animeList;
        this.dataRepository = dataRepository;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).imgUrl()).into(holder.itemImage);
        holder.itemTitle.setText(animeList.get(position).title());
        holder.itemSwitch().setChecked(dataRepository.isFavoriteAnime(animeList.get(position).id()));
        holder.itemSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                if (status){
                   dataRepository.addFavoriteAnime(animeList.get(position));
                } else {
                    dataRepository.deleteFavoriteAnime(animeList.get(position).id());
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", animeList.get(position).title());
                intent.putExtra("synopsis", animeList.get(position).synopsis());
                intent.putExtra("startDate", animeList.get(position).startDate());
                intent.putExtra("endDate", animeList.get(position).endDate());
                intent.putExtra("type", animeList.get(position).type());
                intent.putExtra("episode", animeList.get(position).nbEpisode());
                intent.putExtra("imageURL", animeList.get(position).imgUrl());
                intent.putExtra("id", animeList.get(position).id());
                fragment.launchDetailActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    protected class SearchViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;
        private TextView itemTitle;
        private Switch itemSwitch;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemSwitch = itemView.findViewById(R.id.itemSwitch);
        }

        public ImageView itemImage(){
            return itemImage;
        }

        public TextView itemTitle(){
            return itemTitle;
        }

        public Switch itemSwitch(){
            return itemSwitch;
        }
    }
}
