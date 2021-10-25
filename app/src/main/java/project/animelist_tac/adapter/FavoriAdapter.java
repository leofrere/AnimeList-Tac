package project.animelist_tac.adapter;

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
    public void onBindViewHolder(@NonNull FavoriViewHolder holder, int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).imgUrl()).into(holder.itemImage);
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
