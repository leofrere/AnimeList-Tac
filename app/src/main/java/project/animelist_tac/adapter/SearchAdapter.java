package project.animelist_tac.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import project.animelist_tac.R;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.data.localData.Entity.AnimeEntity;
import project.animelist_tac.model.Anime;
import project.animelist_tac.view.DetailActivity;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Anime> animeList;
    private DataRepository dataRepository;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public SearchAdapter(List<Anime> animeList, DataRepository dataRepository, ActivityResultLauncher<Intent> fragment){
        this.animeList = animeList;
        this.dataRepository = dataRepository;
        this.activityResultLauncher = fragment;
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
        Picasso.with(holder.itemImage.getContext()).load(animeList.get(position).image_url).into(holder.itemImage);
        holder.itemTitle.setText(animeList.get(position).title);
        dataRepository.getFavoriteAnime(animeList.get(position).id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableMaybeObserver<AnimeEntity>() {

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull AnimeEntity animeEntity) {
                        holder.itemSwitch.setChecked(true);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        holder.itemSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean status) {
                if (status){
                    dataRepository.getFavoriteAnime(animeList.get(position).id).subscribeOn(Schedulers.io())
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
                                    dataRepository.addFavoriteAnime(animeList.get(position).asAnimeEntity());
                                }
                            });
                } else {
                    System.out.println("off");
                    dataRepository.deleteFavoriteAnime(animeList.get(position).asAnimeEntity());
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", animeList.get(position).title);
                intent.putExtra("synopsis", animeList.get(position).synopsis);
                intent.putExtra("startDate", animeList.get(position).getStart_date());
                intent.putExtra("endDate", animeList.get(position).getEnd_date());
                intent.putExtra("type", animeList.get(position).type);
                intent.putExtra("episode", animeList.get(position).episodes);
                intent.putExtra("imageURL", animeList.get(position).image_url);
                intent.putExtra("id", animeList.get(position).id);
                activityResultLauncher.launch(intent);
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
