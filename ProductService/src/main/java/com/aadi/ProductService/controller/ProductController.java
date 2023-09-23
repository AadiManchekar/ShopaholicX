package com.aadi.ProductService.controller;

import com.aadi.ProductService.model.ProductRequest;
import com.aadi.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<Long> addProduct(
    @RequestBody ProductRequest productRequest
  ) {
    long productId = productService.addProduct(productRequest);
    return new ResponseEntity<Long>(productId, HttpStatus.CREATED);
  }
}
