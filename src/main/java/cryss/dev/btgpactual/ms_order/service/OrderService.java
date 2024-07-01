package cryss.dev.btgpactual.ms_order.service;

import cryss.dev.btgpactual.ms_order.consumer.dto.OrderCreatedEvent;
import cryss.dev.btgpactual.ms_order.controller.dto.OrderResponse;
import cryss.dev.btgpactual.ms_order.entity.OrderEntity;
import cryss.dev.btgpactual.ms_order.entity.OrderItem;
import cryss.dev.btgpactual.ms_order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@RequiredArgsConstructor
@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final MongoTemplate mongoTemplate;


    public void save(OrderCreatedEvent event) {

        var entity = new OrderEntity ();
        entity.setOrderId (event.codigoCliente ());
        entity.setCustomerId (event.codigoCliente ());
        entity.setItems (getOrderItems (event));
        entity.setTotal (getTotal (event));

        orderRepository.save (entity);
    }


    public Page<OrderResponse> findAllByCustomer(Long customerId, PageRequest pageRequest){
        var ordersEntity = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return ordersEntity.map (OrderResponse::toOrderResponse);
    }


    public BigDecimal getTotal(OrderCreatedEvent event){
        return event.itens ()
                .stream ()
                .map (i-> i.preco ().multiply (BigDecimal.valueOf (i.quantidade ())))
                .reduce (BigDecimal::add)
                .orElse (BigDecimal.ZERO);
    }

    public List<OrderItem> getOrderItems(OrderCreatedEvent event){
        return event.itens ().stream ()
                .map (item-> new OrderItem (item.produto (), item.quantidade (), item.preco ()))
                .toList ();
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId){
        var aggregations = newAggregation (
                match(Criteria.where ("customerId").is (customerId)),
                group().sum("total").as("total")
        );


        var response = mongoTemplate.aggregate (aggregations, "tb_orders", Document.class);


       // return (BigDecimal) response.getUniqueMappedResult ().getOrDefault ("total", BigDecimal.ZERO);

        return new BigDecimal (response.getUniqueMappedResult ().get ("total").toString ());
    }


}
