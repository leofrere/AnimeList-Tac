package project.animelist_tac.viewModel;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.animelist_tac.adapter.SearchAdapter;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.model.Anime;
import project.animelist_tac.view.SearchFragment;

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
