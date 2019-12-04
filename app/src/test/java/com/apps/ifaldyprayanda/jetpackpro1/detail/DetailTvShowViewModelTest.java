package com.apps.ifaldyprayanda.jetpackpro1.detail;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
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

public class DetailTvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvShowViewModel viewModel;
    private JetpackRepository jetpackRepository = mock(JetpackRepository.class);
    private TvShowEntity dummyTvShow = FakeDataDummy.generateDataTv().get(0);
    private String tvId = dummyTvShow.getTvId();


    @Before
    public void setUp()
    {
        viewModel = new DetailTvShowViewModel(jetpackRepository);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getTvs()
    {
        Resource<TvShowEntity> resource = Resource.success(dummyTvShow);

        MutableLiveData<Resource<TvShowEntity>> tvShowEntity = new MutableLiveData<>();
        tvShowEntity.setValue(resource);

        when(jetpackRepository.getTvShowDetailData(tvId)).thenReturn(tvShowEntity);

        Observer<Resource<TvShowEntity>> observer = mock(Observer.class);

        viewModel.getTvs(tvId).observeForever(observer);

        verify(observer).onChanged(resource);
    }
}