package com.apps.ifaldyprayanda.jetpackpro1.detail;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;
import com.apps.ifaldyprayanda.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private MovieDetailViewModel viewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);
    private MovieEntity dummyMovie = FakeDataDummy.generateDataMovie().get(0);
    private String movieId = dummyMovie.getMovieId();

    @Before
    public void setUp()
    {
        viewModel = new MovieDetailViewModel(jetpackRepository);
        viewModel.setMovieId(movieId);
    }

    @Test
    public void getMovies()
    {
        Resource<MovieEntity> resource = Resource.success(dummyMovie);
        MutableLiveData<Resource<MovieEntity>> movieEntities = new MutableLiveData<>();
        movieEntities.setValue(resource);

        when(jetpackRepository.getMovieDetailData(movieId)).thenReturn(movieEntities);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);

        viewModel.getMovies(movieId).observeForever(observer);

        verify(observer).onChanged(resource);
    }

}