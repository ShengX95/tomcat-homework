package com.sx.server;

import com.sx.base.Lifecycle;
import com.sx.utils.MapperWapper;
import com.sx.utils.XMLUtils;

import java.util.Arrays;

/**
 * @author shengx
 * @date 2020/3/26 19:39
 */
public class MyContext implements Lifecycle {
    private String appBase;
    private String docBase;
    private String path;
    private WebappClassLoader classLoader;
    private MapperWapper[] mapperWappers;

    // 初始化context，到context目录的WEB-INF目录下，读取web.xml信息
    public void init() {
        XMLUtils xmlUtils = new XMLUtils();
        classLoader = new WebappClassLoader();
        classLoader.setContextPath(appBase + docBase);
        mapperWappers = xmlUtils.parseServletByContextPath(appBase, docBase, classLoader);
    }

    public void start() {

    }

    public void destory() {

    }

    public String getDocBase() {
        return docBase;
    }

    public void setDocBase(String docBase) {
        this.docBase = docBase;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    public WebappClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(WebappClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public MapperWapper[] getMapperWappers() {
        return mapperWappers;
    }

    public void setMapperWappers(MapperWapper[] mapperWappers) {
        this.mapperWappers = mapperWappers;
    }

    @Override
    public String toString() {
        return "MyContext{" +
                "appBase='" + appBase + '\'' +
                ", docBase='" + docBase + '\'' +
                ", path='" + path + '\'' +
                ", classLoader=" + classLoader +
                ", mapperWappers=" + Arrays.toString(mapperWappers) +
                '}';
    }
}
