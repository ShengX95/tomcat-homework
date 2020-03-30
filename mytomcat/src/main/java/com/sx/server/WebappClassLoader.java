package com.sx.server;

import java.io.*;

/**
 * @author shengx
 * @date 2020/3/26 21:44
 */
public class WebappClassLoader extends ClassLoader {
    private String contextPath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        // 首先，查找该类是否已经被加载过了
//        Class c = findLoadedClass(name);
//        String pathName = name.replace("." , "\\");
//        if(c == null){
//            String path = contextPath + "\\" + pathName + ".class";
//            try {
//                c = getParent().loadClass(name);
//                if(c!=null)
//                    return c;
//                InputStream in = new FileInputStream(new File(path));
//                if(in !=null ){
//                    int size = in.available();
//                    byte[] b = new byte[size];
//                    in.read(b);
//                    return defineClass(name, b, 0, size);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return c;
//    }
}
