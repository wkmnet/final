/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午5:27
 * ---------------------------------
 */
package org.wukm.service;

import com.google.inject.AbstractModule;
import org.wukm.service.impl.UserServiceImpl;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.service
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午5:27
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class ServiceModule extends AbstractModule{

    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
