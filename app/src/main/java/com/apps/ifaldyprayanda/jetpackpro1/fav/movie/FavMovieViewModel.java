package com.apps.ifaldyprayanda.jetpackpro1.fav.movie;

import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class FavMovieViewModel extends ViewModel {

    private JetpackRepository jetpackRepository;

    public FavMovieViewModel(JetpackRepository jetpackRepository) {
        this.jetpackRepository = jetpackRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getFavMovies()
    {
        return jetpackRepository.getFavMovies();
    }

    void setFavMovie(MovieEntity movieEntity)
    {
        final boolean newState = !movieEntity.isFavourited();
        jetpackRepository.setFavMovie(movieEntity, newState);
    }
}
