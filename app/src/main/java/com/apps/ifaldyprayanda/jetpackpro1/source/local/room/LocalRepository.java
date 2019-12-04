package com.apps.ifaldyprayanda.jetpackpro1.source.local.room;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

public class LocalRepository {

    private final JetpackDao mJetpackDao;

    public LocalRepository(JetpackDao jetpackDao) {
        this.mJetpackDao = jetpackDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(JetpackDao jetpackDao)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new LocalRepository(jetpackDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies()
    {
        return mJetpackDao.getAllMovies();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavMovies()
    {
        return mJetpackDao.getFavMovieAsPaged();
    }

    public void insertMovies(List<MovieEntity> movies)
    {
        mJetpackDao.insertMovies(movies);
    }

    public LiveData<List<TvShowEntity>> getAllTvShows(){
        return mJetpackDao.getAllTvShow();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavTvShows()
    {
        return mJetpackDao.getTvShowAsPaged();
    }

    public void insertTvShow(List<TvShowEntity> tvShows)
    {
        mJetpackDao.insertTvShow(tvShows);
    }

    public void setFavMovie(MovieEntity movie, boolean state)
    {
        movie.setFavourited(state);
        mJetpackDao.updateMovie(movie);
    }

    public void setFavTvshow(TvShowEntity tvshow, boolean state)
    {
        tvshow.setFavourited(state);
        mJetpackDao.updateTvShow(tvshow);
    }

    public LiveData<MovieEntity> getDetailMovie(String movieId)
    {
        return mJetpackDao.getMovieDetail(movieId);
    }

    public LiveData<TvShowEntity> getTvShowDetail(String tvId)
    {
        return mJetpackDao.getDetailTvShow(tvId);
    }

}
