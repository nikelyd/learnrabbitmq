package Subscribe;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer1 {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        final Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道绑定交换机
        channel.exchangeDeclare("logs", "Subscribe");
        // 创建临时队列
        String que = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(que,"logs","");
        // 获取消息
        channel.basicConsume(que,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" +new String(body));
            }
        });

    }
}
