package com.thoughtworks.wechatmoment;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor diskExecutor;

    private final Executor ioExecutor;

    private final Executor mainThread;

    public Executor getDiskExecutor() {
        return diskExecutor;
    }

    public Executor getIoExecutor() {
        return ioExecutor;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private AppExecutors(Executor diskExecutor, Executor ioExecutor, Executor mainThread) {
        this.diskExecutor = diskExecutor;
        this.ioExecutor = ioExecutor;
        this.mainThread = mainThread;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }

    private static class MainThreadExecutor implements Executor {

        private Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainHandler.post(command);
        }
    }
}
