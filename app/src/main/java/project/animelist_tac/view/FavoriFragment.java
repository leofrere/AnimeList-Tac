package project.animelist_tac.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import project.animelist_tac.adapter.FavoriAdapter;
import project.animelist_tac.data.DataRepository;
import project.animelist_tac.databinding.FavoriFragmentBinding;
import project.animelist_tac.viewModel.FavoriViewModel;

public class FavoriFragment extends Fragment {
    private FavoriViewModel viewModel;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriFragmentBinding binding;
    private DataRepository dataRepository;
    private ActivityResultLauncher<Intent> startActivityWithResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FavoriFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dataRepository = new DataRepository(view.getContext());
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager = new GridLayoutManager(view.getContext(),3);
        binding.favoriteRecyclerView.setLayoutManager(layoutManager);
        FavoriAdapter favoriAdapter = new FavoriAdapter(dataRepository.getAllFavoriteAnime(), this);
        binding.favoriteRecyclerView.setAdapter(favoriAdapter);
        viewModel = new FavoriViewModel(view);
        FavoriFragment fragment = this;
        startActivityWithResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        FavoriAdapter favoriAdapter = new FavoriAdapter(dataRepository.getAllFavoriteAnime(), fragment);
                        binding.favoriteRecyclerView.setAdapter(favoriAdapter);
                    }
                });

        return view;
    }

    public void launchDetailActivity(Intent intent){
        startActivityWithResult.launch(intent);
    }
}
