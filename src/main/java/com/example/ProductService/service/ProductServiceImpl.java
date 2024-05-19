package com.example.ProductService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductService.entity.Product;
import com.example.ProductService.exception.ProductServiceCustomException;
import com.example.ProductService.model.ProductRequest;
import com.example.ProductService.model.ProductResponse;
import com.example.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) throws ProductServiceCustomException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given Id not found",
                        "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = ProductResponse.builder()
                .ProductName(product.getProductName())
                .productId(product.getProductId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();

        return productResponse;

    }

}
