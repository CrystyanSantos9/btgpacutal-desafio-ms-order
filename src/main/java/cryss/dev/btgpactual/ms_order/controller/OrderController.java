package cryss.dev.btgpactual.ms_order.controller;

import cryss.dev.btgpactual.ms_order.controller.dto.ApiResponse;
import cryss.dev.btgpactual.ms_order.controller.dto.OrderResponse;
import cryss.dev.btgpactual.ms_order.controller.dto.PaginationResponse;
import cryss.dev.btgpactual.ms_order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize){

        var pageResponse = orderService.findAllByCustomer(customerId, PageRequest.of (page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId (customerId);

        return  ResponseEntity.ok (new ApiResponse<> (
                Map.of ("totalOnOrders", totalOnOrders),
                pageResponse.getContent (),
                PaginationResponse.fromPage (pageResponse)
        ));

    }

}