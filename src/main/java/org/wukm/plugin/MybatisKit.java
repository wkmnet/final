/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:00
 * ---------------------------------
 */
package org.wukm.plugin;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create with IntelliJ IDEA
 * Project name : final
 * Package name : org.wukm.plugin
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-5-2
 * Time : 上午11:00
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class MybatisKit {

    private static Logger logger = LoggerFactory.getLogger(MybatisKit.class);

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionManager session;

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        logger.info("set:{}", sqlSessionFactory);
        MybatisKit.sqlSessionFactory = sqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return MybatisKit.sqlSessionFactory;
    }

    public synchronized static SqlSession session(){
        logger.info("use:{}", sqlSessionFactory);
        logger.info("config:{}", sqlSessionFactory.getConfiguration());
        logger.info("config:{}", Thread.currentThread().getName());
        if(null == session) {
            session = SqlSessionManager.newInstance(sqlSessionFactory);
            if (!session.isManagedSessionStarted()) {
                session.startManagedSession();
                logger.info("start:{}", session);
            }
        }
        return session;
    }

    public static void close(){
        logger.info("close:{}", session);
        if(session.isManagedSessionStarted())
        session.close();
    }
}
