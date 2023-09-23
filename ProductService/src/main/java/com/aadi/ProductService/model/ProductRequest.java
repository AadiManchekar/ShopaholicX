package com.aadi.ProductService.model;

import lombok.Data;

@Data
public class ProductRequest {

  private String name;
  private long price;
  private long quantity;
}
