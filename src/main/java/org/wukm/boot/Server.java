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
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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
        logger.info("ROOT DIR:{}", System.getProperty("user.dir"));
        Path root = Paths.get(System.getProperty("user.dir"),"src/main/webapp");
        if(root.toFile().exists()) {
            logger.info("WebApp:{}", root.toUri().toString());
        } else {
            root = Paths.get(System.getProperty("user.dir"),"webapp");
            logger.info("WebApp:{}", root.toUri().toString());
        }
        Path config = Paths.get(System.getProperty("user.dir"),"conf/server.properties");
        File file = config.toFile();
        if(file.exists() && file.isFile()) {
            Path data = Paths.get(System.getProperty("user.dir"),"data");
            logger.info("find config:{}", config.toString());
            Properties properties = new Properties();
            try {
                properties.load(new FileReader(file));
            } catch (IOException ignore){
                //忽略读取配置文件异常
                logger.info("server context:{} port:{}", "/", 8080);
                new JettyServerForIDEA(root.toUri().toString(), 8080, "/").start();
                return;
            }
            String context = properties.getProperty("server.context","/");
            String configPort = properties.getProperty("server.port","8080");
            int port = NumberUtils.toInt(configPort,8080);
            File dataFile = data.toFile();
            if(dataFile.exists()) {
                if(dataFile.isFile()){
                    logger.error("start server fail:{}", data.toAbsolutePath().toString());
                    System.exit(1);
                }
            } else {
                dataFile.mkdir();
            }
            logger.info("server context:{} port:{} data:{}", context, port, data.toAbsolutePath().toString());
            new JettyServer(root.toUri().toString(), port, context, data.toAbsolutePath().toString()).start();
        } else {
            logger.info("not find config:{}", config.toString());
            logger.info("server context:{} port:{}", "/", 8080);
            new JettyServerForIDEA(root.toUri().toString(), 8080, "/").start();
        }
    }
}
