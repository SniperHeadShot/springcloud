package com.bat.springcloud.eureka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka 服务相关事件监听 [ 生产环境需要使用其他媒介通知,如邮件等 ]
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 16:46
 **/
@Slf4j
@Component
public class EurekaStateChangeListener {

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
        log.info("服务注册事件通知... 服务详情:{}", eurekaInstanceRegisteredEvent);
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
        log.info("服务下线事件通知... 服务详情:{}", eurekaInstanceCanceledEvent);
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
        log.info("服务续约事件通知... 服务详情：{}", eurekaInstanceRenewedEvent);
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
        log.info("注册中心启动事件通知... 服务详情：{}", eurekaRegistryAvailableEvent);
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
        log.info("服务启动通知... 服务详情：{}", eurekaServerStartedEvent);
    }
}
