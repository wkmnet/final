/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:37
 * ---------------------------------
 */
package com.jfinal.log;

import com.jfinal.log.factory.slf4j.Slf4jLogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:37
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CustomerHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CustomerHandler(){}

    private static CustomerHandler instance = null;

    protected void slf4j(){
        logger.info("switch logger : {}", "slf4j");
        Log.setDefaultLogFactory(new Slf4jLogFactory());
    }

    public static CustomerHandler getInstance(){
        if(instance == null){
            instance = new CustomerHandler();
        }
        return instance;
    }
}
