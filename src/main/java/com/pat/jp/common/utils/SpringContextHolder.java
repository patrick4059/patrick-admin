package com.pat.jp.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: huangwei
 * @date: 2023-07-12
 * @time: 13:06
 **/
@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;
//    private static final List<CallBack> CALL_BACKS = new ArrayList<>();
    private static boolean addCallback = true;


    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        if (SpringContextHolder.applicationContext != null) {
//            log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
//        }
//        SpringContextHolder.applicationContext = applicationContext;
//        if (addCallback) {
//            for (CallBack callBack : SpringContextHolder.CALL_BACKS) {
//                callBack.executor();
//            }
//            CALL_BACKS.clear();
//        }
//        SpringContextHolder.addCallback = false;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }
    }

}
