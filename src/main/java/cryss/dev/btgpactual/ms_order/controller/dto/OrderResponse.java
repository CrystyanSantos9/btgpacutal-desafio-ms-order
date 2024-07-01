package cryss.dev.btgpactual.ms_order.controller.dto;

import cryss.dev.btgpactual.ms_order.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse (Long orderId,
                             Long customerId,
                             BigDecimal total){

    public static OrderResponse toOrderResponse(OrderEntity entity){
        return  new OrderResponse (
                entity.getOrderId (),
                entity.getCustomerId (),
                entity.getTotal ());
    }
}
