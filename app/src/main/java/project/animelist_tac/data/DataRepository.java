package project.animelist_tac.data;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import project.animelist_tac.data.localData.AccesFavoriteAnimeDatabase;
import project.animelist_tac.data.webData.AnimeWebDatabase;
import project.animelist_tac.model.Anime;
import project.animelist_tac.viewModel.SearchViewModel;

public class DataRepository {

    private AnimeWebDatabase distanteRepository;
    private AccesFavoriteAnimeDatabase favoriteAnimeDatabase;


    public DataRepository(Context context){
        favoriteAnimeDatabase = new AccesFavoriteAnimeDatabase(context);
        distanteRepository = new AnimeWebDatabase();
    }

    //Local
    public void addFavoriteAnime(Anime anime){
        favoriteAnimeDatabase.addFavoriteAnime(anime);
    }

    public void deleteFavoriteAnime(int mal_id){
        favoriteAnimeDatabase.deleteFavoriteAnime(mal_id);
    }

    public Anime getFavoriteAnime(int mal_id){
        return favoriteAnimeDatabase.getFavoriteAnime(mal_id);
    }

    public List<Anime> getAllFavoriteAnime(){
        return favoriteAnimeDatabase.getAllFavoriteAnime();
    }


    //Distance
    public void searchAnime(String search){
        AsyncTaskRunner runner = new AsyncTaskRunner(search);
        runner.execute();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, List<Anime>> {

        private String searchString;
        private List<Anime> animeList;

        public AsyncTaskRunner(String search){
            searchString = search;
        }

        public List<Anime> animeList(){
            return animeList;
        }

        @Override
        protected List<Anime> doInBackground(String... params) {
            try {
                return distanteRepository.getAllAnime(searchString);
            }catch (Exception e){
                return new LinkedList<Anime>() {
                };
            }
        }

        @Override
        protected void onPostExecute(List<Anime> result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(String... text) {
        }
    }
}