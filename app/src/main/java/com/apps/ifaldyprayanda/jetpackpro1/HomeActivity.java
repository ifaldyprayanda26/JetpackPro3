package com.apps.ifaldyprayanda.jetpackpro1;

import android.os.Bundle;
import android.widget.Toast;

import com.apps.ifaldyprayanda.jetpackpro1.fav.FavFragment;
import com.apps.ifaldyprayanda.jetpackpro1.movie.MovieFragment;
import com.apps.ifaldyprayanda.jetpackpro1.tvshow.TvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {

    private final String SELECTED_MENU = "selected_menu";
    private BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment = null;

        if (item.getItemId() == R.id.navigation_movie)
        {
            fragment = MovieFragment.newInstance();
        } else if (item.getItemId() == R.id.navigation_tv) {
            Toast.makeText(this, "This is Tv Show Fragment", Toast.LENGTH_SHORT).show();
            fragment = TvFragment.newInstance();
        }else if (item.getItemId() == R.id.navigtion_fav)
        {
            fragment = FavFragment.newInstance();
            Toast.makeText(this, "This is Fragment Favourite", Toast.LENGTH_SHORT).show();
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit();
        }
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null)
        {
            savedInstanceState.getInt(SELECTED_MENU);
        }else {
            navView.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU, navView.getSelectedItemId());
    }
}
