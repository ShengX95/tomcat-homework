package com.sx.server;

import com.sx.base.Lifecycle;

import java.util.concurrent.*;

/**
 * @author shengx
 * @date 2020/3/26 19:35
 */
public class MyServer implements Lifecycle {
    private Executor executor;
    private MyEngine myEngine;
    private MyConnector myConnector;
    public void init() {
        // init engine
        myEngine = new MyEngine();
        myEngine.init();
        // init executor
        executor = new ThreadPoolExecutor(10, 100, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));
        // init connector
        myConnector = new MyConnector(8080);
        myConnector.setExecutor(executor);
        myConnector.init();
    }

    public void start(){
        myEngine.start();
        myConnector.start();
    }

    public void destory() {
    }
}
