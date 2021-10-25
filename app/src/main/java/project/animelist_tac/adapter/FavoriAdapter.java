package project.animelist_tac.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.animelist_tac.R;
import project.animelist_tac.model.Anime;
import project.animelist_tac.view.DetailActivity;

public class FavoriAdapter extends RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder> {

    private List<Anime> animeList;

    public FavoriAdapter(List<Anime> animeList){
        this.animeList = animeList;
    }

    @NonNull
    public FavoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_1, parent, false);
        return new FavoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).imgUrl()).into(holder.itemImage);

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
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    protected class FavoriViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;

        public FavoriViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.recyclerFavoriteImage);
        }

        public ImageView itemImage(){
            return itemImage;
        }
    }


}
