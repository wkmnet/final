/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午4:04
 * ---------------------------------
 */
package com.jfinal.log;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午4:04
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LoggerSwitch {

    private static CustomerHandler customerHandler = CustomerHandler.getInstance();

    public static void switchSLF4J(){
        customerHandler.slf4j();
    }
}
