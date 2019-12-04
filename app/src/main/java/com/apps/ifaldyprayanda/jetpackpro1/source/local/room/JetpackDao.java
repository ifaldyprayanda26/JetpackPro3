package com.apps.ifaldyprayanda.jetpackpro1.source.local.room;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;

import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface JetpackDao {

//    movie
    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity>> getAllMovies();

    @Query("SELECT * FROM movie where favourited = 1")
    LiveData<List<MovieEntity>> getFavMovie();

    @WorkerThread
    @Query("SELECT * FROM movie WHERE movieId= :movieId")
    LiveData<MovieEntity> getMovieDetail(String movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> movies);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie(MovieEntity movie);

//    TvSho

    @Query("SELECT * FROM tvshow")
    LiveData<List<TvShowEntity>> getAllTvShow();

    @Query("SELECT * FROM tvshow WHERE favourited = 1")
    LiveData<List<TvShowEntity>> getFavTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshow WHERE tvId= :tvId")
    LiveData<TvShowEntity> getDetailTvShow(String tvId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvShow(List<TvShowEntity> tvShows);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvShow(TvShowEntity tvShow);

//    paging
    @Query("SELECT * FROM movie WHERE favourited = 1")
    DataSource.Factory<Integer, MovieEntity> getFavMovieAsPaged();

    @Query("SELECT * FROM tvshow WHERE favourited = 1")
    DataSource.Factory<Integer, TvShowEntity> getTvShowAsPaged();
}
