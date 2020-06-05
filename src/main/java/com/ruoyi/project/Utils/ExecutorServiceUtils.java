package com.ruoyi.project.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 */
public class ExecutorServiceUtils {

    private  static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static synchronized ExecutorService  getExecutorService(){
        return cachedThreadPool;
    }
}
