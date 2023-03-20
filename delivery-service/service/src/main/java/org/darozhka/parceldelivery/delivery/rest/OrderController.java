package org.darozhka.parceldelivery.delivery.rest;

import org.darozhka.parceldelivery.commons.dto.PageDto;
import org.darozhka.parceldelivery.delivery.domain.OrderStatus;
import org.darozhka.parceldelivery.delivery.dto.OrderCreateDto;
import org.darozhka.parceldelivery.delivery.dto.OrderDto;
import org.darozhka.parceldelivery.delivery.dto.OrderUpdateDto;
import org.darozhka.parceldelivery.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author S.Darozhka
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public PageDto<OrderDto> getPage(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "pageSize") Integer pageSize) {
        return PageDto.from(orderService.getOrders(PageRequest.of(page, pageSize)), OrderDto::from);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable(value = "orderId") Integer orderId) {
        return OrderDto.from(orderService.getOrderById(orderId));
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderCreateDto request) {
        return OrderDto.from(
                orderService.save(request.toOrder()));
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrder(@PathVariable(value = "orderId") Integer orderId,
            @RequestBody OrderUpdateDto request) {
        return OrderDto.from(
                orderService.save(request.toOrder(orderId)));
    }

    @PostMapping("/{orderId}/status")
    public void setOrderStatus(@PathVariable(value = "orderId") Integer orderId,
                               @RequestParam(name = "status") OrderStatus status) {
        orderService.updateOrderStatus(orderId,status);
    }

    @PostMapping("/{orderId}/courier")
    public void setOrderStatus(@PathVariable(value = "orderId") Integer orderId,
                               @RequestParam(name = "courierId") Integer courierId) {
        orderService.addOrderCourier(orderId, courierId);
    }
}
