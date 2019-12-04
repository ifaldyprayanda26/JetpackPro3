package com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow;

import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FavTvShowViewModelTest {

    private FavTvShowViewModel viewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);

    @Before
    public void setUp()
    {
        viewModel = new FavTvShowViewModel(jetpackRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getFavTvShow()
    {
        MutableLiveData<Resource<PagedList<TvShowEntity>>> tvShows = new MutableLiveData<>();
        when(jetpackRepository.getFavTvShow()).thenReturn(tvShows);
        Observer<Resource<PagedList<TvShowEntity>>> observer = mock(Observer.class);

        viewModel.getFavTvShow().observeForever(observer);
        assertNotNull(tvShows);
    }
}