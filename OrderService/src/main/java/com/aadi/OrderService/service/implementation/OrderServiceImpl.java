package com.aadi.OrderService.service.implementation;

import com.aadi.OrderService.external.client.ProductService;
import com.aadi.OrderService.model.Order;
import com.aadi.OrderService.model.OrderRequest;
import com.aadi.OrderService.repository.OrderRepository;
import com.aadi.OrderService.service.OrderService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductService productService;

  @Override
  public long placeOrder(OrderRequest orderRequest) {
    log.info(
      "Recieved orderRequest for product id {}",
      orderRequest.getProductId()
    );

    productService.reduceQuantity(
      orderRequest.getProductId(),
      orderRequest.getQuantity()
    );

    log.info("Creating Order with Status CREATED");

    Order order = Order
      .builder()
      .productId(orderRequest.getProductId())
      .quantity(orderRequest.getQuantity())
      .orderDate(Instant.now())
      .orderStatus("CREATED")
      .amount(orderRequest.getTotalAmount())
      .build();

    log.info(
      "Order placed {} for product id {}",
      order.getId(),
      order.getProductId()
    );
    log.debug(
      "Created Order {} for order id {} corresponding to product id {}",
      order,
      order.getId(),
      order.getProductId()
    );

    order = orderRepository.save(order);

    return order.getId();
  }
}
