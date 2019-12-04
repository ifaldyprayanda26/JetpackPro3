package com.apps.ifaldyprayanda.jetpackpro1.source.remote;

import android.os.Handler;

import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.MovieResponse;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.TvShowResponse;
import com.apps.ifaldyprayanda.jetpackpro1.utils.EspressoIdlingResource;
import com.apps.ifaldyprayanda.jetpackpro1.utils.JsonHelper;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long TIME_MILLIS = 2000;

    public RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RemoteRepository(helper);
        }
        return  INSTANCE;
    }

//    untuk memanggil JSON Helper
   public LiveData<ApiResponse<List<MovieResponse>>> getAllMovieAsLiveData()
   {
       EspressoIdlingResource.increment();
       MutableLiveData<ApiResponse<List<MovieResponse>>> resultMovie = new MutableLiveData<>();

       Handler handler = new Handler();
       handler.postDelayed(() -> {
           resultMovie.setValue(ApiResponse.success(jsonHelper.loadMovie()));
           if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
           {
               EspressoIdlingResource.decrement();
           }
       }, TIME_MILLIS);
       return resultMovie;
   }

    public LiveData<ApiResponse<List<TvShowResponse>>> getAllTvShowAsLiveData()
    {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvShowResponse>>> resultTvShow = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultTvShow.setValue(ApiResponse.success(jsonHelper.loadTvShow()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow())
            {
                EspressoIdlingResource.decrement();
            }
        }, TIME_MILLIS);

        return resultTvShow;
    }

//    public interface LoadMoviesCallback
//    {
//        void onAllMoviesReceived(List<MovieResponse> movieResponses);
//
//        void onDataNotAvailable();
//    }
//
//    public interface LoadTvShowCallback
//    {
//        void onAllTvShowsReceived(List<TvShowResponse> tvShowResponses);
//
//        void onDataNotAvailable();
//    }
}
