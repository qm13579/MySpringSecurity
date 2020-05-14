package cn.people.utils.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static ThreadPoolExecutor getPool(){
        return new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }
}
