package com.bat.message.exchange.config.rabbitmq;

import com.bat.commoncode.enums.MsgExchangeEnum;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

/**
 * RabbitMq 连接配置
 *
 * @author ZhengYu
 * @version 1.0 2019/12/8 14:38
 **/
@Configuration
public class RabbitmqConfig {

    /**
     * 配置Rabbitmq连接信息
     *
     * @param host        地址
     * @param port        端口
     * @param username    用户名
     * @param password    密码
     * @param virtualHost 虚拟空间
     * @return org.springframework.amqp.rabbit.connection.ConnectionFactory
     * @author ZhengYu
     */
    @Bean("cloudConnectionFactory")
    @Primary
    public ConnectionFactory cloudConnectionFactory(
            @Value("${spring.rabbitmq.cloud.host}") String host,
            @Value("${spring.rabbitmq.cloud.port}") int port,
            @Value("${spring.rabbitmq.cloud.username}") String username,
            @Value("${spring.rabbitmq.cloud.password}") String password,
            @Value("${spring.rabbitmq.cloud.virtual-host}") String virtualHost) {
        return buildConnectionFactory(host, port, username, password, virtualHost);
    }

    private ConnectionFactory buildConnectionFactory(String host, int port, String username, String password, String virtualHost) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    /**
     * 定义消费者
     *
     * @param connectionFactory 连接信息
     * @return org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory
     * @author ZhengYu
     */
    @Bean("cloudRabbitListenerContainerFactory")
    @Primary
    public RabbitListenerContainerFactory cloudRabbitListenerContainerFactory(@Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        // 设置五个消费者线程
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
        // 设置最大消费者线程数
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
        // 设置消费者标签
        simpleRabbitListenerContainerFactory.setConsumerTagStrategy(tagName -> "云平台Rabbitmq消费者");
        return simpleRabbitListenerContainerFactory;
    }

    /**
     * 初始化交换机和队列及绑定信息
     *
     * @param connectionFactory 连接信息
     * @return org.springframework.amqp.rabbit.core.RabbitAdmin
     * @author ZhengYu
     */
    @Bean("cloudRabbitAdmin")
    @Primary
    public RabbitAdmin cloudRabbitAdmin(@Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        // 初始化死信队列用于处理Rabbitmq未处理成功的消息
        DirectExchange dlxExchange = new DirectExchange(MsgExchangeEnum.MSG_DEAD_LETTER.getExchangeName());
        rabbitAdmin.declareExchange(dlxExchange);
        Queue dlxQueue = new Queue(MsgExchangeEnum.MSG_DEAD_LETTER.getQueueName());
        rabbitAdmin.declareQueue(dlxQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(dlxQueue).to(dlxExchange).with(MsgExchangeEnum.MSG_DEAD_LETTER.getRoutingKey()));

        // 初始化公共广播连接
        TopicExchange broadcastExchange = new TopicExchange(MsgExchangeEnum.MSG_COMMON_BROADCAST.getExchangeName());
        rabbitAdmin.declareExchange(broadcastExchange);
        Queue broadcastQueue = new Queue(
                MsgExchangeEnum.MSG_COMMON_BROADCAST.getQueueName(),
                // 持久化消息队列, rabbitmq重启的时候不需要创建新的队列 默认true
                true,
                // 表示消息队列没有在使用时将被自动删除 默认是false
                false,
                // 表示该消息队列是否只在当前connection生效,默认是false
                false,
                new HashMap<String, Object>(3) {{
                    // 设置队列的消息过期时间
                    put("x-message-ttl", MsgExchangeEnum.MSG_COMMON_BROADCAST.getTtl());
                    // 设置死信队列交换机
                    put("x-dead-letter-exchange", MsgExchangeEnum.MSG_DEAD_LETTER.getExchangeName());
                    // 设置死信队列
                    put("x-dead-letter-routing-key", MsgExchangeEnum.MSG_DEAD_LETTER.getRoutingKey());
                }});
        rabbitAdmin.declareQueue(broadcastQueue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(broadcastQueue).to(broadcastExchange).with(MsgExchangeEnum.MSG_COMMON_BROADCAST.getRoutingKey()));

        return rabbitAdmin;
    }

    @Bean("cloudRabbitTemplate")
    @Primary
    public RabbitTemplate cloudRabbitTemplate(@Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
