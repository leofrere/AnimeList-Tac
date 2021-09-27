package project.animelist_tac.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import project.animelist_tac.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.nav);
        nav.setOnItemSelectedListener(new  NavigationBarView.OnItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment selectedFrag = null;

                switch (item.getItemId()){
                    case R.id.searchFragment:
                        selectedFrag = new SearchFragment();
                        break;
                    case R.id.favoriteFragment:
                        selectedFrag = new FavoriFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFrag).commit();
                return true;
            };

        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();

    }
}