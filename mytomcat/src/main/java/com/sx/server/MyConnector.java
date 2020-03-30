package com.sx.server;

import com.sx.base.Lifecycle;

import java.util.concurrent.Executor;

/**
 * @author shengx
 * @date 2020/3/26 19:44
 */
public class MyConnector implements Lifecycle {
    private BIOEndPoint endPoint;
    private int port;
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public MyConnector(int port) {
        this.port = port;
    }

    public void init() {
        endPoint = new BIOEndPoint(port);
        endPoint.setExecutor(executor);
        endPoint.init();
    }

    public void start() {
        endPoint.start();
    }

    public void destory() {

    }
}
