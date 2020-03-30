import com.sx.server.MyServer;

import java.io.File;

/**
 * @author shengx
 * @date 2020/3/26 19:32
 */
public class Bootstrap {
    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.init();
        myServer.start();
    }
}
