package project.animelist_tac.viewModel;

import android.view.View;

import androidx.fragment.app.Fragment;
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

    public SearchViewModel(SearchFragment fragment) {
        this.fragment = fragment;
        dataRepository = new DataRepository(fragment.getContext());
        dataRepository.searchAnime("att", this);
    }

    public void searchAction(String searchString){
        if (searchString.length() > 2){
            dataRepository.searchAnime(searchString, this);
        }
    }

    public void setAdapter(List<Anime> animeList){
        SearchAdapter adapter = new SearchAdapter(animeList, dataRepository, fragment);
        fragment.searchRecylerView().setAdapter(adapter);
    }

}
