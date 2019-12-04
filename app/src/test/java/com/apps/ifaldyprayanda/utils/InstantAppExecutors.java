package com.apps.ifaldyprayanda.utils;

import com.apps.ifaldyprayanda.jetpackpro1.utils.AppExecutors;

import java.util.concurrent.Executor;

public class InstantAppExecutors extends AppExecutors {
    private static Executor instant = Runnable::run;

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}
