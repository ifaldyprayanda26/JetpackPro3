package com.apps.ifaldyprayanda.jetpackpro1.source;

import com.apps.ifaldyprayanda.jetpackpro1.source.remote.ApiResponse;
import com.apps.ifaldyprayanda.jetpackpro1.utils.AppExecutors;
import com.apps.ifaldyprayanda.jetpackpro1.vo.Resource;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBounceResource<ResultType, RequestType> {

    public NetworkBounceResource(AppExecutors appExecutors) {

        this.mExecutors = appExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromDB();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    protected void onFetchFailed() {
    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {

        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData ->
                result.setValue(Resource.loading(newData))
        );
        result.addSource(apiResponse, response -> {

            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.status) {
                case SUCCESS:
                    mExecutors.diskIO().execute(() -> {

                        saveCallResult(response.body);

                        mExecutors.mainThread().execute(() ->
                                result.addSource(loadFromDB(),
                                        newData -> result.setValue(Resource.success(newData))));

                    });
                    break;

                case EMPTY:
                    mExecutors.mainThread().execute(() ->
                            result.addSource(loadFromDB(),
                                    newData -> result.setValue(Resource.success(newData))));

                    break;
                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource, newData ->
                            result.setValue(Resource.error(response.message, newData)));
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}
