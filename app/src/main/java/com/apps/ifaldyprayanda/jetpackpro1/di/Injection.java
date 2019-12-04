package com.apps.ifaldyprayanda.jetpackpro1.di;

import android.app.Application;

import com.apps.ifaldyprayanda.jetpackpro1.source.JetpackRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.room.JetpackDatabase;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.room.LocalRepository;
import com.apps.ifaldyprayanda.jetpackpro1.source.remote.RemoteRepository;
import com.apps.ifaldyprayanda.jetpackpro1.utils.AppExecutors;
import com.apps.ifaldyprayanda.jetpackpro1.utils.JsonHelper;

public class Injection {

    public static JetpackRepository provideRepository(Application application)
    {
        JetpackDatabase database = JetpackDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.jetpackDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        AppExecutors appExecutors = new AppExecutors();

        return JetpackRepository.getInstance(remoteRepository, localRepository, appExecutors);
    }
}
