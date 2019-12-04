package com.apps.ifaldyprayanda.jetpackpro1.detail;

import android.os.Bundle;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
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

import com.apps.ifaldyprayanda.jetpackpro1.R;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv" ;

    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private TextView textDirector;
    private ImageView imagePoster;
    private ImageView imgBackground;
    private ProgressBar progressBar;

    private DetailTvShowViewModel viewModel;
    private Menu menu;
    private String tvShowId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = obtainViewModel(this);

        progressBar = findViewById(R.id.progressbar_mtop);
        textTitle = findViewById(R.id.tx_movie);
        textDate  = findViewById(R.id.tx_movie_date);
        textDesc = findViewById(R.id.overview);
        textDirector = findViewById(R.id.tx_director);
        imgBackground = findViewById(R.id.imgBackground);
        imagePoster = findViewById(R.id.imageView2);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            tvShowId = extras.getString(EXTRA_TV);
            if (tvShowId != null)
            {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.setTvId(tvShowId);

            }
        }

        viewModel.getTvs(tvShowId).observe(this, tvShowEntity -> {
            if (tvShowEntity != null)
            {
                switch (tvShowEntity.status)
                {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        showTvShowDetail(tvShowEntity.data);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        this.menu = menu;
        viewModel.tvSource.observe(this, tvShowEntityResource -> {
            if (tvShowEntityResource != null)
            {
                switch (tvShowEntityResource.status)
                {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        boolean state = tvShowEntityResource.data.isFavourited();
                        setTvShowFav(state);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "There is an Error", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    private void setTvShowFav(boolean state) {
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
            viewModel.setTvShowFav();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private DetailTvShowViewModel obtainViewModel(DetailTvShowActivity activity)
    {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailTvShowViewModel.class);
    }

    private void showTvShowDetail(TvShowEntity tvShowEntity)
    {
        textTitle.setText(tvShowEntity.getTvTitle());
        textDate.setText(tvShowEntity.getTvDate());
        textDirector.setText(tvShowEntity.getTvCreator());
        textDesc.setText(tvShowEntity.getTvOverview());

        GlideApp.with(getApplicationContext())
                .load(tvShowEntity.getTvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

        GlideApp.with(getApplicationContext())
                .load(tvShowEntity.getTvPhoto())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgBackground);
    }


}
