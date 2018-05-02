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

import com.jfinal.config.*;
import com.jfinal.template.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wukm.controller.HomeController;
import org.wukm.plugin.GlobalInterceptor;
import org.wukm.plugin.MybatisPlugin;

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
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", HomeController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        MybatisPlugin mybatisPlugin = new MybatisPlugin();
        me.add(mybatisPlugin);
        logger.info("start mybatis..");
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new GlobalInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }
}
