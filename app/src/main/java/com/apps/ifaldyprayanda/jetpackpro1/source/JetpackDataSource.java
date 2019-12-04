package com.apps.ifaldyprayanda.jetpackpro1.source;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public interface JetpackDataSource {

    LiveData<Resource<List<MovieEntity>>> getAllMovies();

    LiveData<Resource<MovieEntity>> getMovieDetailData(String movieId);

    LiveData<Resource<PagedList<MovieEntity>>> getFavMovies();

    LiveData<Resource<List<TvShowEntity>>> getAllTvShows();

    LiveData<Resource<TvShowEntity>> getTvShowDetailData(String tvShowId);

    LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShow();

    void setFavMovie(MovieEntity movie, boolean state);

    void setFavTvShow(TvShowEntity tvShow, boolean state);

}
