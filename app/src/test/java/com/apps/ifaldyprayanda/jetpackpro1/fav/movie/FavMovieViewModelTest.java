package com.apps.ifaldyprayanda.jetpackpro1.fav.movie;

import com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow.FavTvShowViewModel;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
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

public class FavMovieViewModelTest {

    private FavMovieViewModel viewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);

    @Before
    public void setUp()
    {
        viewModel = new FavMovieViewModel(jetpackRepository);
    }

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getFavMovies()
    {
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        when(jetpackRepository.getFavMovies()).thenReturn(movies);
        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getFavMovies().observeForever(observer);
        assertNotNull(movies);
    }

}