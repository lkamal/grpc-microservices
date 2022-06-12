package com.digizol.grpc.java.ms.product.service;

import com.digizol.grpc.java.ms.product.controller.Product;
import com.digizol.grpc.java.ms.product.controller.ReviewData;
import com.digizol.grpc.java.ms.product.service.db.ProductDbRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDbRepository productRepository;
    @Autowired
    private ReviewDataRepository reviewDataRepository;

    private Logger logger = LogManager.getLogger(ProductService.class);

    public Product getProduct(String productId) {
        Product product = productRepository.findById(productId);
        if (product.getId() == null) {
            logger.info("Product with id [{}] not found", productId);
            return product;
        }

        logger.info("Product with id [{}] found", productId);
        ReviewData[] reviewDataArray = reviewDataRepository.getReviewData(productId);
        product.setReviews(reviewDataArray);

        logger.info("[{}] Review Data found for Product with id [{}]", reviewDataArray.length, productId);
        return product;
    }

    public Product[] getProducts() {
        List<Product> productList = productRepository.findAll();
        logger.info("Returning [{}] Products", productList.size());
        return productList.toArray(new Product[productList.size()]);
    }
}
