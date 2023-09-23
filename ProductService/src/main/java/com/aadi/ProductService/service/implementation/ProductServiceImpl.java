package com.aadi.ProductService.service.implementation;

import com.aadi.ProductService.entity.Product;
import com.aadi.ProductService.model.ProductRequest;
import com.aadi.ProductService.repository.ProductRepository;
import com.aadi.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public long addProduct(ProductRequest productRequest) {
    log.info("Adding Product");

    Product product = Product
      .builder()
      .productName(productRequest.getName())
      .quantity(productRequest.getQuantity())
      .price(productRequest.getPrice())
      .build();

    // BeanUtils.copyProperties(productRequest, product);
    log.debug("Adding Product: " + product.toString());

    productRepository.save(product);
    log.info("Added Product");

    return product.getProductId();
  }
}
