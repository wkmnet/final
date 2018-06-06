/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 下午2:34
 * ---------------------------------
 */
package org.wukm.plugin;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.wukm.aop.MapperHelper;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 下午2:34
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class GlobalInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
    }
}
