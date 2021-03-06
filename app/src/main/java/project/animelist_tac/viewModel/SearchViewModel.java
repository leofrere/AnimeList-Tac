package project.animelist_tac.viewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import project.animelist_tac.data.DataRepository;
import project.animelist_tac.model.Anime;
import project.animelist_tac.view.SearchFragment;

/**
 * Represent the class for the search view model
 * @author Leo Frere, Jeremy Curoux
 * */
public class SearchViewModel extends ViewModel {

    private SearchFragment fragment;
    private DataRepository dataRepository;

    public MutableLiveData<List<Anime>> getAnimeList() {
        return animeList;
    }

    private MutableLiveData<List<Anime>> animeList;


    public SearchViewModel(Context context) {
        dataRepository = new DataRepository(context);
        dataRepository.searchAnime("att", this);
        animeList = new MutableLiveData<List<Anime>>();
    }

    public void searchAction(String searchString){
        if (searchString.length() > 2){
            dataRepository.searchAnime(searchString, this);
        }
    }

}
