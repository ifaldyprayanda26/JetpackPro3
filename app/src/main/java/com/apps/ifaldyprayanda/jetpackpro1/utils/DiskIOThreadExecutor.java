package com.apps.ifaldyprayanda.jetpackpro1.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor implements Executor {

    private final Executor mDiksIO;

    DiskIOThreadExecutor()
    {
        mDiksIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable command) {
        mDiksIO.execute(command);
    }
}
