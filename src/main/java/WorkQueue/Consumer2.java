package WorkQueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;


public class Consumer2 {

    public static void main(String args[]) throws IOException {

        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 接收消息
        final Channel channel = connection.createChannel();
            // 服务器每次发送1条数据
        channel.basicQos(1);
        channel.queueDeclare("hello",false,false,false,null);
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("C2 - " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
