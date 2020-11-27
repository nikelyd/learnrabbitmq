package Topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

import static utils.constant.RabbitConstant.*;

public class Provider {

    @Test
    public void produce() throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机（名称和类型）
        channel.exchangeDeclare(EXCHANGE_NAME_TOPICS,EXCHANGE_TYPE_TOPICS);
        // 发布信息
        String routingKey = "name.user.save";
        channel.basicPublish(EXCHANGE_NAME_TOPICS,routingKey,null,("交换器topics，路由key："+routingKey).getBytes());
        // 释放资源
        RabbitMQUtils.closeConnection(connection,channel);
    }
}
