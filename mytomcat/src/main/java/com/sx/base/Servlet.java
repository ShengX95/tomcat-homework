package com.sx.base;

import com.sx.server.Request;
import com.sx.server.Response;

public interface Servlet {

    public void service(Request request, Response response);

    public void doGet(Request request, Response response);

    public void doPost(Request request, Response response);
}
