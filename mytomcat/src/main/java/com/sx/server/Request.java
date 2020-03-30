package com.sx.server;

import java.io.InputStream;

/**
 * @author shengx
 * @date 2020/3/26 20:21
 */
public class Request {
    private InputStream in;
    private String method;
    private String host;
    private String uri;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        this.in = in;
    }

    @Override
    public String toString() {
        return "Request{" +
                "in=" + in +
                ", method='" + method + '\'' +
                ", host='" + host + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
