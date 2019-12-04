package com.apps.ifaldyprayanda.jetpackpro1.fav.tvshow;

import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class FavTvShowViewModel extends ViewModel {
    private JetpackRepository mJetpackRepository;

    public FavTvShowViewModel(JetpackRepository mJetpackRepository) {
        this.mJetpackRepository = mJetpackRepository;
    }

    public LiveData<Resource<PagedList<TvShowEntity>>> getFavTvShow()
    {
        return mJetpackRepository.getFavTvShow();
    }

    void setFavTvShows(TvShowEntity tvShowEntity)
    {
        final boolean newState = !tvShowEntity.isFavourited();
        mJetpackRepository.setFavTvShow(tvShowEntity, newState);
    }
}
