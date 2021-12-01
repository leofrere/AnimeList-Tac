package project.animelist_tac.viewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import project.animelist_tac.data.DataRepository;
import project.animelist_tac.data.localData.Entity.AnimeEntity;

public class FavoriViewModel extends ViewModel {

    public MutableLiveData<List<AnimeEntity>> getAnimeEntitiesList() {
        return animeEntitiesList;
    }

    private DataRepository dataRepository;
    private MutableLiveData<List<AnimeEntity>> animeEntitiesList;

    public FavoriViewModel(Context context) {
        dataRepository = new DataRepository(context);
        animeEntitiesList = new MutableLiveData<List<AnimeEntity>>();

    }

    public void setFavori(){
        dataRepository.getAllFavoriteAnime(this);
    }


}
