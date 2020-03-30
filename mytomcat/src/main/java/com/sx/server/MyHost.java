package com.sx.server;

import com.sx.base.Lifecycle;
import com.sx.utils.MapperContext;
import com.sx.utils.XMLUtils;

/**
 * @author shengx
 * @date 2020/3/26 19:38
 */
public class MyHost implements Lifecycle {
    private String name;
    private String appBase;
    private MapperContext[] contexts;

    public void init() {
        XMLUtils xmlUtils = new XMLUtils();
        contexts = xmlUtils.parseContext(this);
        for (MapperContext context : contexts) {
            context.getObject().init();
        }
    }

    public void start() {

    }

    public void destory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MapperContext[] getContexts() {
        return contexts;
    }

    public void setContexts(MapperContext[] contexts) {
        this.contexts = contexts;
    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }
}
