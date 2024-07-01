package cryss.dev.btgpactual.ms_order.consumer;

import cryss.dev.btgpactual.ms_order.consumer.dto.OrderCreatedEvent;
import cryss.dev.btgpactual.ms_order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static cryss.dev.btgpactual.ms_order.configuration.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderCreatedListener {

    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
        log.info ("Message consumed: {}", message);

        orderService.save (message.getPayload ());

    }

}
