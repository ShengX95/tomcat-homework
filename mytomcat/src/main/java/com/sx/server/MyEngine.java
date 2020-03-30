package com.sx.server;

import com.sx.base.Lifecycle;
import com.sx.utils.Mapper;
import com.sx.utils.MapperHost;
import com.sx.utils.XMLUtils;

import java.util.Arrays;

/**
 * @author shengx
 * @date 2020/3/26 19:53
 */
public class MyEngine implements Lifecycle {
    private MapperHost[] hosts;
    private static Mapper mapper = new Mapper();
    public void init() {
        XMLUtils xmlUtils = new XMLUtils();
        hosts = xmlUtils.parseHosts();

        // 初始化host
        for (MapperHost host : hosts) {
            host.getObject().init();
            mapper.addHost(host.getObject());
        }
    }

    public void start() {

    }

    public void destory() {

    }

    public static Mapper getMapper() {
        return mapper;
    }
}
