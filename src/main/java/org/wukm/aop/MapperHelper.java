/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.aop
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午10:46
 * ---------------------------------
 */
package org.wukm.aop;

import com.jfinal.aop.Enhancer;
import org.apache.ibatis.session.SqlSession;
import org.wukm.plugin.MybatisKit;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.aop
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午10:46
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class MapperHelper {

    private static ConcurrentHashMap<Thread,SqlSession> concurrentHashMap = new ConcurrentHashMap<>();

    private MapperHelper(){}

    public synchronized static <T> T mapper(Class<T> target){
        Thread current = Thread.currentThread();
        if(concurrentHashMap.get(current) == null){
            SqlSession session = MybatisKit.session();
            concurrentHashMap.put(current,session);
            return session.getMapper(target);
        }
        return concurrentHashMap.get(current).getMapper(target);
    }

    public synchronized static void close(){
        Thread current = Thread.currentThread();
        if(concurrentHashMap.get(current) != null){
            MybatisKit.close(concurrentHashMap.get(current));
            concurrentHashMap.remove(current);
        }
    }
}
