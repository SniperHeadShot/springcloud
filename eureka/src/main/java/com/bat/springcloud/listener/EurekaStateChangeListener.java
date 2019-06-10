package com.bat.springcloud.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName EurekaStateChangeListener
 * @Description Eureka 服务相关事件监听 [ 生产环境需要使用其他媒介通知,如邮件等 ]
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/6/10 13:16
 **/
@Component
public class EurekaStateChangeListener {

    private Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    /**
     * 服务注册事件通知
     *
     * @param eurekaInstanceRegisteredEvent
     * @return void
     * @author ZhengYu
     * @date 2019/6/10
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        logger.info("服务注册事件通知... 服务详情:{}", eurekaInstanceRegisteredEvent.toString());
    }

    /**
     * 服务下线事件通知
     *
     * @param eurekaInstanceCanceledEvent
     * @return void
     * @author ZhengYu
     * @date 2019/6/10
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        logger.info("服务下线事件通知... 服务详情:{}", eurekaInstanceCanceledEvent.toString());
    }

    /**
     * 服务续约事件通知
     *
     * @param eurekaInstanceRenewedEvent
     * @return void
     * @author ZhengYu
     * @date 2019/6/10
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent) {
        logger.info("服务续约事件通知... 服务详情：{}", eurekaInstanceRenewedEvent.toString());
    }

    /**
     * 注册中心启动事件通知
     *
     * @param eurekaRegistryAvailableEvent
     * @return void
     * @author ZhengYu
     * @date 2019/6/10
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent eurekaRegistryAvailableEvent) {
        logger.info("注册中心启动事件通知... 服务详情：{}", eurekaRegistryAvailableEvent.toString());
    }

    /**
     * 服务启动通知
     *
     * @param eurekaServerStartedEvent
     * @return void
     * @author ZhengYu
     * @date 2019/6/10
     */
    @EventListener
    public void listen(EurekaServerStartedEvent eurekaServerStartedEvent) {
        logger.info("服务启动通知... 服务详情：{}", eurekaServerStartedEvent.toString());
    }
}
