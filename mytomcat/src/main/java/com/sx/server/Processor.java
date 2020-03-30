package com.sx.server;

import com.sx.base.Servlet;
import com.sx.utils.*;

import java.util.Arrays;

/**
 * @author shengx
 * @date 2020/3/26 19:45
 */
public class Processor {
    private Request request;
    private Response response;
    public Processor(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void process(){
        System.out.println(request);
//        try {
            Servlet servlet = findServlet();
            servlet.service(request, response);
//            String resp = "hello world";
//            long length = resp.getBytes().length;
//            resp = HttpProtocolUtil.getHttpHeader200(length) + resp;
//            response.getOut().write(resp.getBytes());
//            response.getOut().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private Servlet findServlet() {
        Servlet servlet = null;
        String uri = request.getUri();
        String host = request.getHost();
        Mapper mapper = MyEngine.getMapper();
        MapperHost[] hosts = mapper.getHosts();
        for (MapperHost mapperHost : hosts) {
            // 匹配host
            if(host.equals(mapperHost.getObject().getName())){
                // 匹配context
                MapperContext[] contexts = mapperHost.getObject().getContexts();
                for (MapperContext context : contexts) {
                    WebappClassLoader classLoader = context.getObject().getClassLoader();
                    Thread.currentThread().setContextClassLoader(classLoader);
                    if(uri.contains(context.getObject().getPath())){
                        // 找servlet
                        MapperWapper[] mapperWappers = context.getObject().getMapperWappers();
                        System.out.println(Arrays.toString(mapperWappers));
                        for (MapperWapper mapperWapper : mapperWappers) {
                            Wapper wapper = mapperWapper.getObject();
                            if(uri.contains(wapper.getPath())){
                                // 执行servlet
                                return wapper.getServlet();
                            }
                        }
                    }
                }
            }
        }
        return servlet;
    }
}
