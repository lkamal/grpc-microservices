package com.digizol.grpc.java.ms.product.service;

import com.digizol.grpc.java.ms.product.controller.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(String productId);
    List<Product> findAll();
}
