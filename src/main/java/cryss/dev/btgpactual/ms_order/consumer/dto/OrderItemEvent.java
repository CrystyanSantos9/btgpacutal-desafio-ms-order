package cryss.dev.btgpactual.ms_order.consumer.dto;

import java.math.BigDecimal;

public record OrderItemEvent (String produto, Integer quantidade, BigDecimal preco){};