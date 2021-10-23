package project.animelist_tac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.model.Anime;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Anime> animeList;
    private DataRepository dataRepository;

    public SearchAdapter(List<Anime> animeList, DataRepository dataRepository){
        this.animeList = animeList;
        this.dataRepository = dataRepository;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).imgUrl()).into(holder.itemImage);
        holder.itemTitle.setText(animeList.get(position).title());
        holder.itemSwitch().setChecked(dataRepository.isFavoriteAnime(animeList.get(position).id()));
        holder.itemSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                if (status){
                    System.out.println(animeList.get(position).synopsis());
                   dataRepository.addFavoriteAnime(animeList.get(position));
                } else {
                    dataRepository.deleteFavoriteAnime(animeList.get(position).id());
                }
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
