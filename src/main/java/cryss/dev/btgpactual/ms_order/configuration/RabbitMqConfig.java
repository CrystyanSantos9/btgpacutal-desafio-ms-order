package cryss.dev.btgpactual.ms_order.configuration;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter ();
    }

//    @Bean
//    public MessageConverter messageConverter() {
//        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//        return jackson2JsonMessageConverter;
//    }

//    @Bean
//    Queue queue() {
//        return new Queue(ORDER_CREATED_QUEUE, false);
//    }


    @Bean
    public Declarable orderCreatedQueue(){
        return new Queue (ORDER_CREATED_QUEUE);
    }



//    @Bean
//    @Qualifier("myRabbitListenerContainer")
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer ();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames (ORDER_CREATED_QUEUE);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver, MessageConverter jackson2JsonMessageConverter) {
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter ();
//
//        listenerAdapter.setMessageConverter (jackson2JsonMessageConverter);
//        listenerAdapter.setDelegate (receiver);
//        listenerAdapter.setDefaultListenerMethod ("receiveMessage");
//
//        return listenerAdapter;
//    }


}
