package com.sx.servlet;

import com.sx.base.Servlet;
import com.sx.server.Request;
import com.sx.server.Response;
import com.sx.utils.HttpProtocolUtil;

import java.io.IOException;

/**
 * @author shengx
 * @date 2020/3/28 14:54
 */
public class MyServlet implements Servlet {
    public void service(Request request, Response response) {
        System.out.println("doservice2:" + request);
        try {
            String resp = "hello world";
            long length = resp.getBytes().length;
            resp = HttpProtocolUtil.getHttpHeader200(length) + resp;
            response.getOut().write(resp.getBytes());
            response.getOut().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet(Request request, Response response) {

    }

    public void doPost(Request request, Response response) {

    }
}
