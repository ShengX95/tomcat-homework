package com.sx.server;

import com.sx.base.Servlet;

/**
 * @author shengx
 * @date 2020/3/26 21:19
 */
public class Wapper {
    private Servlet servlet;
    private String path;

    public Servlet getServlet() {
        return servlet;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
