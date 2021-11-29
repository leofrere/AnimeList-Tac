package project.animelist_tac.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.animelist_tac.adapter.FavoriAdapter;
import project.animelist_tac.data.localData.Entity.AnimeEntity;
import project.animelist_tac.databinding.FavoriFragmentBinding;
import project.animelist_tac.viewModel.FavoriViewModel;

public class FavoriFragment extends Fragment {
    private FavoriViewModel viewModel;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriFragmentBinding binding;
    private ActivityResultLauncher<Intent> startActivityWithResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager = new GridLayoutManager(view.getContext(),3);
        binding.favoriteRecyclerView.setLayoutManager(layoutManager);
        viewModel = new FavoriViewModel(getContext());
        subscribeViewModel();
        viewModel.setFavori();
        createStartActivityResult();
        return view;
    }

    private void createStartActivityResult() {
        startActivityWithResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                       viewModel.setFavori();
                    }
                });
    }

    private void subscribeViewModel(){
        viewModel.getAnimeEntitiesList().observe(getViewLifecycleOwner(), new Observer<List<AnimeEntity>>() {
            @Override
            public void onChanged(List<AnimeEntity> animeList) {
                System.out.println(animeList);
                binding.favoriteRecyclerView.setAdapter(new FavoriAdapter(animeList, startActivityWithResult));
            }
        });
    }
}
