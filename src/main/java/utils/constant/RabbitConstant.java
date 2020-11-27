package utils.constant;

public class RabbitConstant {
    // 交换机名称
    public static final String EXCHANGE_NAME_ROUTING = "logs_direct";
    public static final String EXCHANGE_NAME_SUBSCRIBE = "logs";
    public static final String EXCHANGE_NAME_TOPICS = "topics";

    // 交换机路由key名称
    public static final String ROUTING_KEY_1 = "info";
    public static final String ROUTING_KEY_2 = "error";

    // 交换机连接类型
    public static final String EXCHANGE_TYPE_DIRECT = "direct";
    public static final String EXCHANGE_TYPE_SUBSCRIBE = "Subscribe";
    public static final String EXCHANGE_TYPE_TOPICS = "topic";

}
