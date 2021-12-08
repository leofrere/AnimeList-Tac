package project.animelist_tac.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.data.localData.Entity.AnimeEntity;
import project.animelist_tac.view.DetailActivity;

/**
 * Represent the class for the favori adapter
 * @author Leo Frere, Jeremy Curoux
 * */
public class FavoriAdapter extends RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder> {

    private List<AnimeEntity> animeList;
    private DataRepository dataRepository;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public FavoriAdapter(List<AnimeEntity> animeList,DataRepository dataRepository ,ActivityResultLauncher<Intent> activityResultLauncher){
        this.animeList = animeList;
        this.dataRepository = dataRepository;
        this.activityResultLauncher = activityResultLauncher;
    }

    @NonNull
    public FavoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycler_item, parent, false);
        return new FavoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).getImage_url()).into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataRepository.updateAndDisplayFavoriteAnime(animeList.get(position), activityResultLauncher);
            }
        });

        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dataRepository.deleteFavoriteAnime(animeList.get(position));
                removeLocalDataSet(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,getItemCount());
            }
        });
    }

    private void removeLocalDataSet(int position) {
        animeList.remove(position);
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    protected class FavoriViewHolder extends RecyclerView.ViewHolder{

        public Button itemButton;
        private ImageView itemImage;

        public FavoriViewHolder(@NonNull View itemView) {
            super(itemView);
            itemButton = itemView.findViewById(R.id.favoriteDelete);
            itemImage = itemView.findViewById(R.id.recyclerFavoriteImage);
        }

        public ImageView itemImage(){
            return itemImage;
        }

        public Button itemButton(){
            return itemButton;
        }
    }


}
