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
import org.wukm.aop.MapperHelper;
import org.wukm.mapper.UserMapper;
import org.wukm.model.User;
import org.wukm.plugin.MybatisKit;

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

    public void index(){
        UserMapper mapper = MapperHelper.mapper(UserMapper.class);
        User user = mapper.findUser(10000000L);
        if(user != null) {
            logger.info("user:{}", user.getUserName());
        }
        renderJson(user);
    }
}
