package com.apps.ifaldyprayanda.jetpackpro1.movie;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private JetpackRepository jetpackRepository;

    public MovieViewModel(JetpackRepository mJetpackRepository) {
        this.jetpackRepository = mJetpackRepository;
    }

    LiveData<Resource<List<MovieEntity>>> getMovies()
    {
        return jetpackRepository.getAllMovies();
    }
}
