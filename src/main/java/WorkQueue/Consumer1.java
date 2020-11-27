package WorkQueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;


public class Consumer1 {

    public static void main(String args[]) throws IOException {

        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 接收消息
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("hello",false,false,false,null);
        channel.basicConsume("hello",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C1 - " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
