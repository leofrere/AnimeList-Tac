package project.animelist_tac.data;

import android.content.Context;
import android.util.Log;

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
import project.animelist_tac.viewModel.FavoriViewModel;
import project.animelist_tac.viewModel.SearchViewModel;

public class DataRepository {

    private FavoriteDatabase favoriteDatabase;


    public DataRepository(Context context) {
        favoriteDatabase = Room.databaseBuilder(context, FavoriteDatabase.class, "favoritedatabase")
                .build();
    }

    //Local
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


    //Distance
    public void searchAnime(String search, SearchViewModel viewModel){
        Single<Response> result = JinkanClient.getInstance().getMyApi().getAnimes(search)
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
