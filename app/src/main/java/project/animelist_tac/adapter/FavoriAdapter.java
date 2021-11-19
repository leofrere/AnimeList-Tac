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
import project.animelist_tac.view.FavoriFragment;
import project.animelist_tac.view.SearchFragment;

public class FavoriAdapter extends RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder> {

    private List<Anime> animeList;
    private FavoriFragment fragment;

    public FavoriAdapter(List<Anime> animeList, FavoriFragment fragment){
        this.animeList = animeList;
        this.fragment = fragment;
    }

    @NonNull
    public FavoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycler_item, parent, false);
        return new FavoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).image_url).into(holder.itemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", animeList.get(position).title);
                intent.putExtra("synopsis", animeList.get(position).synopsis);
                intent.putExtra("startDate", animeList.get(position).start_date);
                intent.putExtra("endDate", animeList.get(position).end_date);
                intent.putExtra("type", animeList.get(position).type);
                intent.putExtra("episode", animeList.get(position).episodes);
                intent.putExtra("imageURL", animeList.get(position).image_url);
                intent.putExtra("id", animeList.get(position).id);
                fragment.launchDetailActivity(intent);
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
