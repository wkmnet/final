/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log.factory.slf4j
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:42
 * ---------------------------------
 */
package com.jfinal.log.factory.slf4j;

import com.jfinal.log.ILogFactory;
import com.jfinal.log.Log;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log.factory.slf4j
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:42
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Slf4jLogFactory implements ILogFactory {
    @Override
    public Log getLog(Class<?> clazz) {
        return new Slf4jLog(clazz);
    }

    @Override
    public Log getLog(String name) {
        return new Slf4jLog(name);
    }
}
