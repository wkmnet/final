/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:41
 * ---------------------------------
 */
package org.wukm.controller;

import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wukm.kit.Gu;
import org.wukm.model.User;
import org.wukm.service.UserService;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.controller
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:41
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class HomeController extends Controller{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void index() throws Exception{
        UserService userService = Gu.gu(UserService.class);
        User user = userService.findUserById(10000000L);
        if(user != null) {
            logger.info("user:{}", user.getUserName());
        }
        renderJson(user);
    }
}
