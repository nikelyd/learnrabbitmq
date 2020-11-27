package HelloWorld;

import utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    // 生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        /**
         * 第一步：创建virtual host
         * 第二步：连接virtual host
         * 第三步：发送数据
         * 第四步：关闭连接
         */

        // 第二步：获取连接通道
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);

        // 第三步：发布消息
        channel.basicPublish("","hello",null,"我TM暴富！！".getBytes());

        // 第四步：关闭连接
        RabbitMQUtils.closeConnection(connection,channel);
    }
}
