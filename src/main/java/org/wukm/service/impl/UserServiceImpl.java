/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.service.impl
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午4:43
 * ---------------------------------
 */
package org.wukm.service.impl;

import com.google.inject.Inject;
import org.wukm.mapper.UserMapper;
import org.wukm.model.User;
import org.wukm.service.CommonService;
import org.wukm.service.UserService;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.service.impl
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午4:43
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class UserServiceImpl extends CommonService implements UserService {

    @Inject
    UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.findUser(id);
    }
}
