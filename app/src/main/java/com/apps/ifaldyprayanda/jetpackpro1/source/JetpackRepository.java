package com.apps.ifaldyprayanda.jetpackpro1.source;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.room.LocalRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.ApiResponse;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.RemoteRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.MovieResponse;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.TvShowResponse;
import com.apps.ifaldyprayanda.jetpackpro1.utils.AppExecutors;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class JetpackRepository implements JetpackDataSource {

    private volatile static JetpackRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;
    private final int listNumber = 10;


    public JetpackRepository(@NonNull RemoteRepository remoteRepository, @NonNull LocalRepository localRepository, AppExecutors appExecutors) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;
    }

    public static JetpackRepository getInstance(RemoteRepository remoteData, LocalRepository localRepository, AppExecutors appExecutors)
    {
        if (INSTANCE == null)
        {
            synchronized (JetpackRepository.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new JetpackRepository(remoteData, localRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {
        return new NetworkBounceResource<List<MovieEntity>, List<MovieResponse>>(appExecutors)
        {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return  localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getAllMovieAsLiveData();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieResponse movieResponse: data)
                {
                    movieEntities.add(new MovieEntity(movieResponse.getMovieId(),
                            movieResponse.getMovieTitle(),
                            movieResponse.getMovieDate(),
                            movieResponse.getMoviePhoto(),
                            movieResponse.getMovieLanguage(),
                            movieResponse.getMovieOverview()));
                }
                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieDetailData(String movieId) {
        return new NetworkBounceResource<MovieEntity, MovieResponse>(appExecutors)
        {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getDetailMovie(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavMovies() {
        return new NetworkBounceResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors)
        {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavMovies(), listNumber).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvShowEntity>>> getAllTvShows() {
        return new NetworkBounceResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            @Override
            protected LiveData<List<TvShowEntity>> loadFromDB() {
                return localRepository.getAllTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return  remoteRepository.getAllTvShowAsLiveData();
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TvShowResponse tvShowResponse: data)
                {
                    tvShowEntities.add(new TvShowEntity(tvShowResponse.getTvId(),
                            tvShowResponse.getTvTitle(),
                            tvShowResponse.getTvDate(),
                            tvShowResponse.getTvPhoto(),
                            tvShowResponse.getTvLanguage(),
                            tvShowResponse.getTvOverview()));
                }
                localRepository.insertTvShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvShowDetailData(String tvShowId) {
        return new NetworkBounceResource<TvShowEntity, TvShowResponse>(appExecutors)
        {
            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localRepository.getTvShowDetail(tvShowId);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<TvShowResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(TvShowResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShow() {
        return new NetworkBounceResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors)
        {
            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavTvShows(), listNumber).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvShowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavMovie(MovieEntity movie, boolean state) {
        Runnable runnable = () -> localRepository.setFavMovie(movie, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setFavTvShow(TvShowEntity tvShow, boolean state) {
        Runnable runnable = () -> localRepository.setFavTvshow(tvShow, state);
        appExecutors.diskIO().execute(runnable);
    }

}
