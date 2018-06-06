/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.kit
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午6:09
 * ---------------------------------
 */
package org.wukm.kit;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jfinal.plugin.IPlugin;
import org.mybatis.guice.XMLMyBatisModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wukm.service.ServiceModule;
import org.wukm.util.Commons;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.kit
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午6:09
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class GuExtendPlugin implements IPlugin {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String projectPackage = "org.wukm";

    @Override
    public boolean start() {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId("development");
            }
        }, new ServiceModule());
        Gu.registerInjector(injector);
        if(logger.isDebugEnabled())
        logger.debug("createInjector:{}", injector);
        Commons.packageName(projectPackage).forEach(name -> {
            String packageName = projectPackage.concat(".").concat(name);
            if(logger.isDebugEnabled()) {
                logger.debug("packageName:{}", packageName);
                logger.debug("className:{}", Commons.className(packageName));
            }
        });
        return true;
    }

    @Override
    public boolean stop() {
        logger.info("close Gu {}", true);
        return true;
    }
}
