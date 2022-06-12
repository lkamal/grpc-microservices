package com.digizol.grpc.java.ms.product.service.db;

import com.digizol.grpc.java.ms.product.controller.Product;
import com.digizol.grpc.java.ms.product.service.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductDbRepository implements ProductRepository {

    private Map<String, Product> productDB = new HashMap<>();

    @PostConstruct
    private void populateDB() {
        int i = 1;
        {
            String productId = "prod-" + i++;
            Product product = new Product(productId, "iPhone 13 Pro Max", 1450);
            productDB.put(productId, product);
        }
        {
            String productId = "prod-" + i++;
            Product product = new Product(productId, "Samsung Galaxy S22 Ultra", 1099);
            productDB.put(productId, product);
        }
        {
            String productId = "prod-" + i++;
            Product product = new Product(productId, "Google Pixel 6", 999);
            productDB.put(productId, product);
        }
    }

    @Override
    public Product findById(String productId) {
        Product product = productDB.get(productId);
        if (product == null) {
            product = new Product();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productDB.values().stream().collect(Collectors.toList());
    }
}
