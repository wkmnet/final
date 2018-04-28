/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log.factory.slf4j
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:40
 * ---------------------------------
 */
package com.jfinal.log.factory.slf4j;

import com.jfinal.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : com.jfinal.log.factory.slf4j
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-4-28
 * Time : 下午3:40
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Slf4jLog extends Log {

    private Logger log;

    Slf4jLog(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }

    Slf4jLog(String name) {
        log = LoggerFactory.getLogger(name);
    }

    public static Slf4jLog getLog(Class<?> clazz) {
        return new Slf4jLog(clazz);
    }

    public static Slf4jLog getLog(String name) {
        return new Slf4jLog(name);
    }

    public void info(String message) {
        log.info(message);
    }

    public void info(String message, Throwable t) {
        log.info(message, t);
    }

    public void debug(String message) {
        log.debug(message);
    }

    public void debug(String message, Throwable t) {
        log.debug( message, t);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void warn(String message, Throwable t) {
        log.warn( message, t);
    }

    public void error(String message) {
        log.error( message);
    }

    public void error(String message, Throwable t) {
        log.error( message, t);
    }

    public void fatal(String message) {
        log.trace( message);
    }

    public void fatal(String message, Throwable t) {
        log.trace( message, t);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isFatalEnabled() {
        return log.isTraceEnabled();
    }
}
