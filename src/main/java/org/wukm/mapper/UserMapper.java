/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.mapper
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:12
 * ---------------------------------
 */
package org.wukm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.wukm.model.User;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.mapper
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:12
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public interface UserMapper {

    @Select("select id,user_name from user_base where id=#{id}")
    User findUser(@Param("id")Long id);
}
