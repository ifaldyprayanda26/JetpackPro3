package com.apps.ifaldyprayanda.jetpackpro1.tvshow;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    private JetpackRepository jetpackRepository;

    public TvShowViewModel(JetpackRepository mJetpackRepository) {
        this.jetpackRepository = mJetpackRepository;
    }

    LiveData<Resource<List<TvShowEntity>>> getTvShows()
    {
        return jetpackRepository.getAllTvShows();
    }
}
