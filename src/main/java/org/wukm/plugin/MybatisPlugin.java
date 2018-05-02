/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午10:52
 * ---------------------------------
 */
package org.wukm.plugin;

import com.jfinal.plugin.IPlugin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午10:52
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class MybatisPlugin implements IPlugin {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean start() {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            MybatisKit.setSqlSessionFactory(sqlSessionFactory);
            logger.info("create sqlSessionFactory:{}", sqlSessionFactory);
        } catch (IOException e){
            logger.error("IOException:{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
