package com.sx.server;

import com.sx.base.Lifecycle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Executor;

/**
 * @author shengx
 * @date 2020/3/26 19:45
 */
public class BIOEndPoint implements Lifecycle {
    private int port;
    private ServerSocket serverSocket;
    private Executor executor;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public BIOEndPoint(int port) {
        this.port = port;
    }

    public void init() {
        try {
            serverSocket = new ServerSocket();
            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocket.bind(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true){
                Socket socket = serverSocket.accept();
                SocketProcessor socketProcessor = new SocketProcessor(socket);
                executor.execute(socketProcessor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destory() {

    }
}
