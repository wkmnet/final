/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.kit
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午6:04
 * ---------------------------------
 */
package org.wukm.kit;

import com.google.inject.Injector;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.kit
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-6
 * Time : 下午6:04
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Gu {

    private static Injector injector;

    public static void registerInjector(Injector injector){
        Gu.injector = injector;
    }
    public static <T> T gu(Class<T> type){
        return injector.getInstance(type);
    }
}
