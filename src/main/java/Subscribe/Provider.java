package Subscribe;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

import static utils.constant.RabbitConstant.EXCHANGE_NAME_SUBSCRIBE;
import static utils.constant.RabbitConstant.EXCHANGE_TYPE_SUBSCRIBE;

public class Provider {

    @Test
    public void publish() throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 连接交换机
        channel.exchangeDeclare(EXCHANGE_NAME_SUBSCRIBE, EXCHANGE_TYPE_SUBSCRIBE);
        // 发送消息
        channel.basicPublish(EXCHANGE_NAME_SUBSCRIBE,"",null,"今天不下雨".getBytes());
        // 释放资源
        RabbitMQUtils.closeConnection(connection,channel);
    }

}
