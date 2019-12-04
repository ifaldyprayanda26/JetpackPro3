package com.apps.ifaldyprayanda.jetpackpro1.detail;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class DetailTvShowViewModel extends ViewModel {

    private MutableLiveData<String> tvShowId = new MutableLiveData<>();

    private JetpackRepository jetpackRepository;

    public DetailTvShowViewModel(JetpackRepository mJetpackRepository) {
        this.jetpackRepository = mJetpackRepository;
    }

    LiveData<Resource<TvShowEntity>> getTvs(String tvShowId)
    {
        return jetpackRepository.getTvShowDetailData(tvShowId);
    }

    public void setTvId(String tvId)
    {
        this.tvShowId.setValue(tvId);
    }

    public String getTvId()
    {
        if (tvShowId.getValue() == null) return null;
        return tvShowId.getValue();
    }

    public LiveData<Resource<TvShowEntity>> tvSource = Transformations.switchMap(tvShowId,
            tvShowId -> jetpackRepository.getTvShowDetailData(tvShowId));

//    set FavTvShowData
    void setTvShowFav()
    {
        if (tvSource.getValue() != null)
        {
            TvShowEntity tvEntity = tvSource.getValue().data;

            final boolean state = !tvEntity.isFavourited();
            jetpackRepository.setFavTvShow(tvEntity, state);
        }
    }
}
