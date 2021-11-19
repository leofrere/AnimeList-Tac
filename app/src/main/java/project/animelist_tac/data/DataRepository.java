package project.animelist_tac.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import project.animelist_tac.data.localData.AccesFavoriteAnimeDatabase;
import project.animelist_tac.data.webData.JinkanClient;
import project.animelist_tac.model.Anime;
import project.animelist_tac.model.Response;
import project.animelist_tac.viewModel.SearchViewModel;

public class DataRepository {

    private List<Anime> animeList = new ArrayList<>();
    private AccesFavoriteAnimeDatabase favoriteAnimeDatabase;


    public DataRepository(Context context){
        favoriteAnimeDatabase = new AccesFavoriteAnimeDatabase(context);
    }

    //Local
    public void addFavoriteAnime(Anime anime){
        favoriteAnimeDatabase.addFavoriteAnime(anime);
    }

    public void deleteFavoriteAnime(int mal_id){
        favoriteAnimeDatabase.deleteFavoriteAnime(mal_id);
    }

    public boolean isFavoriteAnime(int mal_id){
        return favoriteAnimeDatabase.isFavoriteAnime(mal_id);
    }

    public Anime getFavoriteAnime(int mal_id){
        return favoriteAnimeDatabase.getFavoriteAnime(mal_id);
    }

    public List<Anime> getAllFavoriteAnime(){
        return favoriteAnimeDatabase.getAllFavoriteAnime();
    }


    //Distance
    public void searchAnime(String search, SearchViewModel viewModel){
        Single<Response> result = JinkanClient.getInstance().getMyApi().getAnimes(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        result.subscribeWith(new DisposableSingleObserver<Response>() {

            @Override
            public void onSuccess(@NonNull Response response) {
                viewModel.setAdapter(response.results);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("LF", e.getMessage());
            }
        });
    }
}
