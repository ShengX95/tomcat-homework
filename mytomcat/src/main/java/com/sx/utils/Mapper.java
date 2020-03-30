package com.sx.utils;

import com.sx.server.MyHost;

import java.util.Arrays;
import java.util.Map;

/**
 * @author shengx
 * @date 2020/3/26 21:14
 */
public class Mapper {
    private MapperHost[] hosts = new MapperHost[0];

    public void addHost(MyHost host){
        if(hosts.length == 0){
            hosts = new MapperHost[1];
            MapperHost mapperHost = new MapperHost(host.getName(), host);
            hosts[0] = mapperHost;
        } else {
            MapperHost[] newMapperHosts = new MapperHost[hosts.length + 1];
            System.arraycopy(hosts,0, newMapperHosts, 0, hosts.length);
            hosts = newMapperHosts;
        }
    }

    public MapperHost[] getHosts() {
        return hosts;
    }

}
