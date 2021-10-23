package project.animelist_tac.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.animelist_tac.R;
import project.animelist_tac.databinding.SearchFragmentBinding;
import project.animelist_tac.viewModel.SearchViewModel;

public class SearchFragment extends Fragment {
    private SearchViewModel viewModel;
    private RecyclerView.LayoutManager layoutManager;
    private SearchFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SearchFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        layoutManager = new LinearLayoutManager(view.getContext());
        binding.searchRecycler.setLayoutManager(layoutManager);
        viewModel = new SearchViewModel(this);
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

    public RecyclerView searchRecylerView(){
        return binding.searchRecycler;
    }
}
