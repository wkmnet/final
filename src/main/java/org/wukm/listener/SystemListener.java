/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.listener
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午4:01
 * ---------------------------------
 */
package org.wukm.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfinal.log.LoggerSwitch;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.listener
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午4:01
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class SystemListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("JFinal server start...");
        JSONObject env = new JSONObject();
        System.getenv().forEach((key,value) -> {
            env.put(key,value);
        });
        logger.info("system evn:{}{}", SystemUtils.LINE_SEPARATOR, JSONObject.toJSONString(env, SerializerFeature.PrettyFormat));
        JSONObject property = new JSONObject();
        System.getProperties().stringPropertyNames().forEach(key ->{
            property.put(key, System.getProperty(key));
        });
        logger.info("system property:{}{}", SystemUtils.LINE_SEPARATOR, JSONObject.toJSONString(property, SerializerFeature.PrettyFormat));
        LoggerSwitch.switchSLF4J();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
