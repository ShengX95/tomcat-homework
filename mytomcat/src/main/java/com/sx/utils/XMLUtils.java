package com.sx.utils;

import com.sx.base.Servlet;
import com.sx.server.MyContext;
import com.sx.server.MyHost;
import com.sx.server.Wapper;
import com.sx.server.WebappClassLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author shengx
 * @date 2020/3/29 20:29
 */
public class XMLUtils {
    private static String SERVER_XML_PATH = "server.xml";

    public MapperHost[] parseHosts() {
        SAXReader reader = new SAXReader();
        MapperHost[] mapperHosts = null;
        try {
            InputStream in = XMLUtils.class.getClassLoader().getResourceAsStream(SERVER_XML_PATH);
            Document doc = reader.read(in);
            List<Element> hostNodes = doc.selectNodes("//host");
            if (hostNodes.size() > 0) {
                mapperHosts = new MapperHost[hostNodes.size()];
                for (int i = 0; i < hostNodes.size(); i++) {
                    Element node = hostNodes.get(i);
                    String name = node.attributeValue("name");
                    String appBase = node.attributeValue("appBase");
                    MyHost myHost = new MyHost();
                    myHost.setName(name);
                    myHost.setAppBase(appBase);
                    MapperHost mapperHost = new MapperHost(name, myHost);
                    mapperHosts[i] = mapperHost;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapperHosts;
    }

    public MapperContext[] parseContext(MyHost myHost) {
        SAXReader reader = new SAXReader();
        InputStream in = XMLUtils.class.getClassLoader().getResourceAsStream(SERVER_XML_PATH);
        MapperContext[] mapperContexts = null;
        try {
            Document doc = reader.read(in);
            String appBase = myHost.getAppBase();
            String name = myHost.getName();
            List<Element> contextNodes = doc.selectNodes("//host[@name='" + name + "']/context");
            if (contextNodes.size() > 0) {
                mapperContexts = new MapperContext[contextNodes.size()];
                for (int i = 0; i < contextNodes.size(); i++) {
                    Element node = contextNodes.get(i);
                    String docBase = node.attributeValue("docBase");
                    String path = node.attributeValue("path");
                    MyContext myContext = new MyContext();
                    myContext.setDocBase(docBase);
                    myContext.setPath(path);
                    myContext.setAppBase(appBase);
                    WebappClassLoader classLoader = new WebappClassLoader();
                    classLoader.setContextPath(appBase + "/" + docBase);
                    MapperContext mapperContext = new MapperContext(docBase, myContext);
                    mapperContexts[i] = mapperContext;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapperContexts;
    }

    public MapperWapper[] parseServletByContextPath(String appBase, String docBase, WebappClassLoader classLoader) {
        String webxmlPath = appBase + docBase + "\\WEB-INF\\web.xml";
        MapperWapper[] wappers = null;
        try {
            InputStream in = new FileInputStream(new File(webxmlPath));
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            List<Element> list = doc.selectNodes("//servlet");
            if(list.size()>0){
                wappers = new MapperWapper[list.size()];
                for (int i=0;i<list.size();i++) {
                    Element element = list.get(i);
                    System.out.println(element);
                    Element nameNode = element.element("servlet-name");
                    String className = nameNode.getText();
                    Node pathNode = element.element("servlet-mapping");
                    String path = pathNode.getText();
                    Wapper wapper = new Wapper();
                    wapper.setPath(path);
                    Class<?> aClass = classLoader.loadClass(className);
                    Object instance = aClass.newInstance();
                    wapper.setServlet((Servlet) instance);
                    wappers[i] = new MapperWapper(className, wapper);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wappers;
    }
}
