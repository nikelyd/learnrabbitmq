package Routing;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

import static utils.constant.RabbitConstant.*;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机和交换类型
        channel.exchangeDeclare(EXCHANGE_NAME_ROUTING,"direct");
        // 创建临时队列
        String que = channel.queueDeclare().getQueue();
        // 基于key绑定队列和交换机
        channel.queueBind(que,EXCHANGE_NAME_ROUTING,ROUTING_KEY_1);
        // 获取消息
        channel.basicConsume(que,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
