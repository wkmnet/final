/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:57
 * ---------------------------------
 */
package org.wukm.config;

import com.jfinal.aop.Duang;
import com.jfinal.config.*;
import com.jfinal.template.Engine;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:57
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class SystemConfig extends JFinalConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configConstant(Constants me) {
        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSessionFactory.openSession();
            logger.error("sqlSessionFactory:{}", sqlSessionFactory);
        } catch (IOException e){
            logger.error("IOException:{}", e.getMessage());
        }
    }

    @Override
    public void configRoute(Routes me) {

    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
