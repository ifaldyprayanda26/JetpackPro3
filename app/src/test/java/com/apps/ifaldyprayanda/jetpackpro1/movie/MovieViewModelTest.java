package com.apps.ifaldyprayanda.jetpackpro1.movie;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
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

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);


    @Before
    public void setUp()
    {
        viewModel = new MovieViewModel(jetpackRepository);
    }

    @Test
    public void getMovies()
    {
        Resource<List<MovieEntity>> resource = Resource.success(FakeDataDummy.generateDataMovie());
        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(resource);

        when(jetpackRepository.getAllMovies()).thenReturn(movies);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(resource);
    }

}