package com.aadi.OrderService.controller;

import com.aadi.OrderService.model.OrderRequest;
import com.aadi.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/v1/placeOrder")
  public ResponseEntity<Long> placeOrder(
    @RequestBody OrderRequest orderRequest
  ) {
    log.info("Placing order for {}", orderRequest.getProductId());
    log.debug(
      "OrderRequest for productId {} is {}",
      orderRequest.getProductId(),
      orderRequest
    );
    long orderId = orderService.placeOrder(orderRequest);

    return new ResponseEntity<>(orderId, HttpStatus.OK);
  }
}
