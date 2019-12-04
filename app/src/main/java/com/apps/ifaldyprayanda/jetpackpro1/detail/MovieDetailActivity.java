package com.apps.ifaldyprayanda.jetpackpro1.detail;

import android.os.Bundle;

import com.apps.ifaldyprayanda.jetpackpro1.R;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.utils.GlideApp;
import com.apps.ifaldyprayanda.jetpackpro1.viewmodel.ViewModelFactory;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private TextView textDirector;
    private ImageView imagePoster;
    private ImageView imgBackground;
    private ProgressBar progressBar;

    private MovieDetailViewModel viewModel;
    private String movieId;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = obtainViewModel(this);

        progressBar = findViewById(R.id.progressbar_detail);
        textTitle = findViewById(R.id.tx_movie);
        textDate  = findViewById(R.id.tx_movie_date);
        textDesc = findViewById(R.id.overview);
        textDirector = findViewById(R.id.tx_director);
        imgBackground = findViewById(R.id.imgBackground);
        imagePoster = findViewById(R.id.imageView2);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            movieId = extras.getString(EXTRA_MOVIE);
            if (movieId != null)
            {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.setMovieId(movieId);
            }
        }

        viewModel.getMovies(movieId).observe(this, movieEntity -> {
            if (movieEntity != null)
            {
                switch (movieEntity.status)
                {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        showMovieDetail(movieEntity.data);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        this.menu = menu;
        viewModel.movieSource.observe(this, movieEntityResource -> {
            if (movieEntityResource != null)
            {
                switch(movieEntityResource.status)
                {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        boolean state = movieEntityResource.data.isFavourited();
                        setFavourite(state);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "There is an Error", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    private void setFavourite(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_fav);
        if (state)
        {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp));
            Toast.makeText(this, "Favourite Data", Toast.LENGTH_SHORT).show();
        }else
        {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_fav)
        {
            viewModel.setFavMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private MovieDetailViewModel obtainViewModel(AppCompatActivity activity)
    {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel.class);
    }

    private void showMovieDetail(MovieEntity movieEntity)
    {

        textTitle.setText(movieEntity.getMovieTitle());
        textDate.setText(movieEntity.getMovieDate());
        textDirector.setText(movieEntity.getMovieDirector());
        textDesc.setText(movieEntity.getMovieOverview());

        GlideApp.with(getApplicationContext())
                .load(movieEntity.getMoviePhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

        GlideApp.with(getApplicationContext())
                .load(movieEntity.getMoviePhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgBackground);
    }
}
