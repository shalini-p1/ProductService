package com.example.ProductService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private String ProductName;
    private long productId;
    private long price;
    private long quantity;
}
