/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-7
 * Time : 上午11:34
 * ---------------------------------
 */
package org.wukm.interceptor;

import com.google.inject.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wukm.kit.Gu;

import java.lang.reflect.Field;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-6-7
 * Time : 上午11:34
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class GuBeanInterceptor implements Interceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        for(Field field : controller.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Inject.class)){
                if(field.getType().isInterface()){
                    setValue(field,controller, Gu.gu(field.getType()));
                }
            }
            logger.info("field:{}",field.getName());
        }
        inv.invoke();
    }

    private void setValue(Field field, Controller controller, Object value){
        try {
            field.setAccessible(true);
            field.set(controller, value);
        } catch (IllegalArgumentException ignore) {
            //ignore
        } catch (IllegalAccessException ignore){
            //ignore
        }
    }
}
