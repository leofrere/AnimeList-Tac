package project.animelist_tac.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.animelist_tac.adapter.SearchAdapter;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.databinding.SearchFragmentBinding;
import project.animelist_tac.model.Anime;
import project.animelist_tac.viewModel.SearchViewModel;

public class SearchFragment extends Fragment {
    private SearchViewModel viewModel;
    private RecyclerView.LayoutManager layoutManager;
    private SearchFragmentBinding binding;
    private ActivityResultLauncher<Intent> startActivityWithResult;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        createStartActivityResult();
        binding = SearchFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        layoutManager = new LinearLayoutManager(view.getContext());
        binding.searchRecycler.setLayoutManager(layoutManager);
        viewModel = new SearchViewModel(this.getContext());
        subscribeViewModel();

        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                viewModel.searchAction(s);
                binding.searchBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return view;
    }

    private void createStartActivityResult() {
        startActivityWithResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        String searchString = String.valueOf(binding.searchBar.getQuery());
                        if (searchString.length() < 3){
                            searchString = "att";
                        }
                        viewModel.searchAction(searchString);
                    }
                });
    }

    private void subscribeViewModel(){
        viewModel.getAnimeList().observe(getViewLifecycleOwner(), new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> animeList) {
                binding.searchRecycler.setAdapter(new SearchAdapter(animeList, new DataRepository(getContext()), startActivityWithResult));
            }
        });
    }
}
