package com.bat.message.exchange.config.websocket;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpointConfig;

/**
 * 解决 WebSocketServerEndpoint 无法注入Bean的问题
 *
 * @author ZhengYu
 * @version 1.0 2019/12/8 16:45
 **/
@Component
public class SpringContextHelper extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHelper.context = applicationContext;
    }
}
