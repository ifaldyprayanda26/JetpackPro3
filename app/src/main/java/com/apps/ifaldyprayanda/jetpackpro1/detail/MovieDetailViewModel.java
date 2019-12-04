package com.apps.ifaldyprayanda.jetpackpro1.detail;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {
    private JetpackRepository jetpackRepository;

    private MutableLiveData<String> movieId = new MutableLiveData<>();

    public MovieDetailViewModel(JetpackRepository mJetpackRepository) {
        this.jetpackRepository = mJetpackRepository;
    }

    LiveData<Resource<MovieEntity>> getMovies(String movieId)
    {
        return jetpackRepository.getMovieDetailData(movieId);
    }

    public void setMovieId(String movieId)
    {
        this.movieId.setValue(movieId);
    }

    public String getMovieId()
    {
        if (movieId.getValue() == null) return null;
        return movieId.getValue();
    }

    public LiveData<Resource<MovieEntity>> movieSource = Transformations.switchMap(movieId,
            movieId -> jetpackRepository.getMovieDetailData(movieId));

//    set FavouriteMovieData
    public void setFavMovie()
    {
        if (movieSource.getValue() != null)
        {
            MovieEntity mEntity = movieSource.getValue().data;

            final boolean newState = !mEntity.isFavourited();
            jetpackRepository.setFavMovie(mEntity, newState);
        }
    }

}
