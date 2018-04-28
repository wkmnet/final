/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:30
 * ---------------------------------
 */
package org.wukm.boot;

import com.jfinal.server.JettyServerForIDEA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:30
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Server {

    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args){
        Path root = Paths.get(System.getProperty("user.dir"),"src/main/webapp");
        logger.info("ROOT:{}", root.toUri().toString());
        new JettyServerForIDEA(root.toUri().toString(),8080,"/").start();
    }
}
