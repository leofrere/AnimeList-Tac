package project.animelist_tac.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.animelist_tac.adapter.FavoriAdapter;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.databinding.FavoriFragmentBinding;
import project.animelist_tac.viewModel.FavoriViewModel;

public class FavoriFragment extends Fragment {
    private FavoriViewModel viewModel;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriFragmentBinding binding;
    private DataRepository dataRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FavoriFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dataRepository = new DataRepository(view.getContext());
        layoutManager = new LinearLayoutManager(view.getContext());
        binding.favoriteRecyclerView.setLayoutManager(layoutManager);
        FavoriAdapter favoriAdapter = new FavoriAdapter(dataRepository.getAllFavoriteAnime());
        binding.favoriteRecyclerView.setAdapter(favoriAdapter);
        viewModel = new FavoriViewModel(view);

        return view;
    }
}
