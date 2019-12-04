package com.apps.ifaldyprayanda.jetpackpro1.tvshow;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;
import com.apps.ifaldyprayanda.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private TvShowViewModel tvShowViewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);

    @Before
    public void setUp()
    {

        tvShowViewModel = new TvShowViewModel(jetpackRepository);
    }

    @Test
    public void getTvShows()
    {
        Resource<List<TvShowEntity>> resource = Resource.success(FakeDataDummy.generateDataTv());

        MutableLiveData<Resource<List<TvShowEntity>>> tvShows = new MutableLiveData<>();
        tvShows.setValue(resource);

        when(jetpackRepository.getAllTvShows()).thenReturn(tvShows);

        Observer<Resource<List<TvShowEntity>>> observer = mock(Observer.class);

        tvShowViewModel.getTvShows().observeForever(observer);

        verify(observer).onChanged(resource);
    }

}