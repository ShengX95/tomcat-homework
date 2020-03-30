package com.sx.server;

import java.io.OutputStream;

/**
 * @author shengx
 * @date 2020/3/26 20:21
 */
public class Response {
    private OutputStream out;

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }
}
