package com.azurelc.robot.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPoolUtil {
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(1);

    private ThreadPoolUtil() {
    }

    public static ExecutorService getThreadPool() {
        return THREAD_POOL;
    }
}
