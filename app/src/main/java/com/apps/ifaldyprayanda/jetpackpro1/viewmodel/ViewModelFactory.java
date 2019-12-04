package com.apps.ifaldyprayanda.jetpackpro1.viewmodel;

import android.app.Application;

import com.apps.ifaldyprayanda.jetpackpro1.detail.DetailTvShowViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.detail.MovieDetailViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.di.Injection;
import com.apps.ifaldyprayanda.jetpackpro1.fav.movie.FavMovieViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow.FavTvShowViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.movie.MovieViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.tvshow.TvShowViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final JetpackRepository mJetpackRepository;

    private ViewModelFactory(JetpackRepository jetpackRepository)
    {
        mJetpackRepository = jetpackRepository;
    }

    public static ViewModelFactory getInstance(Application application)
    {
        if (INSTANCE == null)
        {
            synchronized (ViewModelFactory.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class))
        {
            return (T) new MovieViewModel(mJetpackRepository);
        }else if (modelClass.isAssignableFrom(MovieDetailViewModel.class))
        {
            return (T) new MovieDetailViewModel(mJetpackRepository);
        }else if (modelClass.isAssignableFrom(TvShowViewModel.class))
        {
            return  (T) new TvShowViewModel(mJetpackRepository);
        }else if(modelClass.isAssignableFrom(DetailTvShowViewModel.class))
        {
            return (T) new DetailTvShowViewModel(mJetpackRepository);
        }else if (modelClass.isAssignableFrom(FavMovieViewModel.class))
        {
            return (T) new FavMovieViewModel(mJetpackRepository);
        }else if (modelClass.isAssignableFrom(FavTvShowViewModel.class))
        {
            return (T) new FavTvShowViewModel(mJetpackRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
