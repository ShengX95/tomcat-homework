package com.sx.server;

import sun.awt.windows.WBufferStrategy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author shengx
 * @date 2020/3/26 20:18
 */
public class SocketProcessor implements Runnable {
    private Socket socket;

    public SocketProcessor(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // 读取socket输入数据
            socket.setSoTimeout(6000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Request request = new Request();
            Response response = new Response();
            int count = 0;
            while (count == 0){
                count = in.available();
            }
            byte buffer[] = new byte[count];
            in.read(buffer);
            String requestStr = new String(buffer);
            System.out.println(requestStr);
            // 封装http到Request
            String[] sp = requestStr.split("\\r\\n");
            String head = sp[0];
            String heads[] = head.split(" ");
            String method = heads[0];
            String uri = heads[1];
            String host = null;
            for (String s : sp) {
                if(s.contains("Host:")){
                    host = s.substring(6);
                }
            }
            request.setIn(in);
            request.setUri(uri);
            request.setHost(host);
            request.setMethod(method);
            response.setOut(out);
            // 将Request和Response传递给processor进行处理
            Processor processor = new Processor(request, response);
            processor.process();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
