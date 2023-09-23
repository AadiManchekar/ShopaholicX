package com.aadi.ProductService.service.implementation;

import com.aadi.ProductService.entity.Product;
import com.aadi.ProductService.model.ProductRequest;
import com.aadi.ProductService.model.ProductResponse;
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

    log.debug("Adding Product: " + product.toString());

    productRepository.save(product);
    log.info("Added Product");

    return product.getProductId();
  }

  @Override
  public ProductResponse getProductById(long productId) {
    log.info("GET the product for productId {}", productId);
    Product product = productRepository
      .findById(productId)
      .orElseThrow(() -> new RuntimeException("pwoduct with given id not found")
      );

    log.debug("Fetched Product {}", product);
    ProductResponse productResponse = new ProductResponse();
    BeanUtils.copyProperties(product, productResponse);

    log.debug("returning Product {}", productResponse);

    return productResponse;
  }
}
