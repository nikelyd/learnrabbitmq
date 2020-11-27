package WorkQueue;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;
import com.rabbitmq.client.Connection;

import java.io.IOException;


public class Provider {
    @Test
    public void publish() throws IOException {
        // 1. 拿到连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 发送消息
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","hello",null,("武力值 + "+i) .getBytes());
        }
        // 3. 关闭连接
        RabbitMQUtils.closeConnection(connection,channel);
    }
}
