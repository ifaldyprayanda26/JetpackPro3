package com.apps.ifaldyprayanda.jetpackpro1.source;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.room.LocalRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.RemoteRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.MovieResponse;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.response.TvShowResponse;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;
import com.apps.ifaldyprayanda.utils.FakeDataDummy;
import com.apps.ifaldyprayanda.utils.InstantAppExecutors;
import com.apps.ifaldyprayanda.utils.LiveDataUtils;
import com.apps.ifaldyprayanda.utils.PagedListUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JetpackRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository localRepository = mock(LocalRepository.class);
    private RemoteRepository remote = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeJetpackRepository jetpackRepository = new FakeJetpackRepository(localRepository, remote, instantAppExecutors);

    private ArrayList<MovieResponse> movieResponses = FakeDataDummy.generateRemoteMovie();
    private List<MovieEntity> movieEntities = FakeDataDummy.generateDataMovie();
    private String movieId = movieResponses.get(0).getMovieId();
    private List<TvShowEntity> tvShowEntities = FakeDataDummy.generateDataTv();
    private ArrayList<TvShowResponse> tvShowResponses = FakeDataDummy.generateRemoteTvShow();
    private String tvShowId = tvShowResponses.get(0).getTvId();


    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }


    @Test
    public void getAllMovies()
    {
        MutableLiveData<List<MovieEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(FakeDataDummy.generateDataMovie());

        when(localRepository.getAllMovies()).thenReturn(dummyMovies);

        Resource<List<MovieEntity>> result = LiveDataUtils.getValue(jetpackRepository.getAllMovies());

        verify(localRepository).getAllMovies();
        assertNotNull(result.data);
        assertEquals(movieResponses.size(), result.data.size());
    }

    @Test
    public void getAllTvShows()
    {
        MutableLiveData<List<TvShowEntity>> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(FakeDataDummy.generateDataTv());

        when(localRepository.getAllTvShows()).thenReturn(dummyTvShow);

        Resource<List<TvShowEntity>> result = LiveDataUtils.getValue(jetpackRepository.getAllTvShows());

        verify(localRepository).getAllTvShows();
        assertNotNull(result.data);
        assertEquals(tvShowResponses.size(), result.data.size());
    }

    @Test
    public void getMovieDetailData()
    {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.getMovie(movieId));

        when(localRepository.getDetailMovie(movieId)).thenReturn(dummyEntity);
        Resource<MovieEntity> result = LiveDataUtils.getValue(jetpackRepository.getMovieDetailData(movieId));

        verify(localRepository).getDetailMovie(movieId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertNotNull(result.data.getMovieTitle());
        assertNotNull(result.data.getMovieOverview());
        assertEquals(FakeDataDummy.generateDataMovie().get(0).getMovieOverview(), result.data.getMovieOverview());
    }

    @Test
    public void getTvShowDetailData()
    {
        MutableLiveData<TvShowEntity> dummmyTvShow = new MutableLiveData<>();
        dummmyTvShow.setValue(FakeDataDummy.getTv(tvShowId));

        when(localRepository.getTvShowDetail(tvShowId)).thenReturn(dummmyTvShow);
        Resource<TvShowEntity> result = LiveDataUtils.getValue(jetpackRepository.getTvShowDetailData(tvShowId));

        verify(localRepository).getTvShowDetail(tvShowId);
        assertNotNull(result);
        assertNotNull(result.data);
        assertNotNull(result.data.getTvTitle());
        assertNotNull(result.data.getTvOverview());
        assertEquals(FakeDataDummy.generateDataTv().get(0).getTvOverview(), result.data.getTvOverview());
    }

    @Test
    public void getFavMovies()
    {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavMovies()).thenReturn(dataSourceFactory);

        jetpackRepository.getFavMovies();

        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movieEntities));

        verify(localRepository).getFavMovies();
        assertNotNull(result.data);
        assertEquals(movieEntities.size(), result.data.size());
    }

    @Test
    public void getFavTvShow()
    {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.getFavTvShows()).thenReturn(dataSourceFactory);

        jetpackRepository.getFavTvShow();

        Resource<PagedList<TvShowEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvShowEntities));

        verify(localRepository).getFavTvShows();
        assertNotNull(result.data);
        assertEquals(tvShowEntities.size(), result.data.size());
    }

}