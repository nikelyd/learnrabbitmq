package Routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

import static utils.constant.RabbitConstant.*;

public class provider {
    @Test
    public void publish() throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME_ROUTING,EXCHANGE_TYPE_DIRECT);
        // 发送消息
        String key1 = ROUTING_KEY_1;
        String key2 = ROUTING_KEY_2;
        channel.basicPublish(EXCHANGE_NAME_ROUTING,key1,null,("direct模型key为："+key1).getBytes());
        channel.basicPublish(EXCHANGE_NAME_ROUTING,key2,null,("direct模型key为："+key2).getBytes());
        // 释放资源
        RabbitMQUtils.closeConnection(connection,channel);
    }
}
