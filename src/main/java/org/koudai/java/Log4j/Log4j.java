package org.koudai.java.Log4j;

import jdk.nashorn.internal.runtime.logging.Loggable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @description Log4j的使用
 * https://blog.csdn.net/lailai186/article/details/81866597
 * @Author: 韩金铭 1329674322@qq.com
 * @Date: 2019/8/4 14:01
 */
public class Log4j {
    private static Logger logger = LogManager.getLogger(Log4j.class.getName());

    /**
     * log4j规定了默认的几个级别：trace<debug<info<warn<error<fatal等
     */
    public void getLog4j(){

        logger.trace("我是trace");
        logger.info("info信息");
        logger.error("我是error");
        logger.fatal("我是fatal");

        logger.trace("退出程序");
        System.exit(0);
    }
    public static void main(String[] args) {
        new Log4j().getLog4j()  ;
    }
    }
