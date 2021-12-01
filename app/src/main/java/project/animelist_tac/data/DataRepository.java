package project.animelist_tac.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.room.Room;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import project.animelist_tac.data.localData.Entity.AnimeEntity;
import project.animelist_tac.data.localData.FavoriteDatabase;
import project.animelist_tac.data.webData.JinkanClient;
import project.animelist_tac.model.Response;
import project.animelist_tac.view.DetailActivity;
import project.animelist_tac.viewModel.FavoriViewModel;
import project.animelist_tac.viewModel.SearchViewModel;

public class DataRepository {

    private FavoriteDatabase favoriteDatabase;
    private Context context;


    public DataRepository(Context context) {
        this.context = context;
        favoriteDatabase = Room.databaseBuilder(context, FavoriteDatabase.class, "favoritedatabase")
                .build();
    }

    public void addFavoriteAnime(AnimeEntity anime){
        favoriteDatabase.getWriteExecutor().execute(() -> {
            favoriteDatabase.AnimeDao().insert(anime);
        });
    }

    public void deleteFavoriteAnime(AnimeEntity animeEntity){
        favoriteDatabase.getWriteExecutor().execute(() -> {
            favoriteDatabase.AnimeDao().delete(animeEntity);
        });
    }

    public Maybe<AnimeEntity> getFavoriteAnime(int mal_id){
        return favoriteDatabase.AnimeDao().getAnimeEntity(mal_id);
    }

    public void getAllFavoriteAnime(FavoriViewModel viewModel){
        Single<List<AnimeEntity>> favoriteAnimes = favoriteDatabase.AnimeDao().getAllAnime();
        favoriteAnimes.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<AnimeEntity>>() {

                    @Override
                    public void onSuccess(@NonNull List<AnimeEntity> animeEntities) {
                        viewModel.getAnimeEntitiesList().setValue(animeEntities);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }


    public void updateAndDisplayFavoriteAnime(AnimeEntity animeEntity, ActivityResultLauncher<Intent> activityResultLauncher){
        Single<Response> result = JinkanClient.getInstance().getMyApi().getAnimes(animeEntity.getTitle(), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        result.subscribeWith(new DisposableSingleObserver<Response>() {

            @Override
            public void onSuccess(@NonNull Response response) {
                favoriteDatabase.getWriteExecutor().execute(() -> {
                    favoriteDatabase.AnimeDao().update(response.results.get(0).asAnimeEntity());
                });
                Intent intent = new Intent(context, DetailActivity.class);
                prepareIntent(intent, response.results.get(0).asAnimeEntity());
                activityResultLauncher.launch(intent);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("LF", e.getMessage());
            }
        });
    }

    private void prepareIntent(Intent intent, AnimeEntity anime) {
        intent.putExtra("title", anime.getTitle());
        intent.putExtra("synopsis", anime.getSynopsis());
        intent.putExtra("startDate", anime.getStart_date());
        intent.putExtra("endDate", anime.getEnd_date());
        intent.putExtra("type", anime.getType());
        intent.putExtra("episode", anime.getEpisodes());
        intent.putExtra("imageURL", anime.getImage_url());
        intent.putExtra("id", anime.getMal_id());
    }

    public void searchAnime(String search, SearchViewModel viewModel){
        Single<Response> result = JinkanClient.getInstance().getMyApi().getAnimes(search, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        result.subscribeWith(new DisposableSingleObserver<Response>() {

            @Override
            public void onSuccess(@NonNull Response response) {
                viewModel.getAnimeList().setValue(response.results);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("LF", e.getMessage());
            }
        });
    }
}
