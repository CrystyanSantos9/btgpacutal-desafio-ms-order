package cryss.dev.btgpactual.ms_order.consumer.dto;

import cryss.dev.btgpactual.ms_order.entity.OrderItem;

import java.util.List;

public record OrderCreatedEvent(Long codigoPedido, Long codigoCliente, List<OrderItemEvent> itens ) {
}
