package project.animelist_tac.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import project.animelist_tac.R;
import project.animelist_tac.viewModel.FavoriViewModel;

public class FavoriFragment extends Fragment {
    private FavoriViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favori_fragment, container, false);
        viewModel = new FavoriViewModel(view);

        return view;
    }
}
