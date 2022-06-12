package com.digizol.grpc.java.ms.product.controller;

import com.digizol.grpc.java.ms.product.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    private Logger logger = LogManager.getLogger(ProductController.class);

    @GetMapping
    @ResponseBody
    public ResponseEntity<Product[]> getProducts() {
        logger.info("Get Products");
        Product[] products = service.getProducts();
        logger.info("Returning {} Product(s)", products.length);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {
        logger.info("Get Product for productId {}", productId);
        final Product product = service.getProduct(productId);

        if (product.getId() == null) {
            logger.info("No Product found for productId {}", productId);
            return ResponseEntity.notFound().build();
        }

        logger.info("Returning {}", product);
        return ResponseEntity.ok(product);
    }

}
