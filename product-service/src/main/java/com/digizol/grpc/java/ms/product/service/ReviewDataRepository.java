package com.digizol.grpc.java.ms.product.service;

import com.digizol.grpc.java.ms.product.controller.ReviewData;

public interface ReviewDataRepository {
    ReviewData[] getReviewData(String productId);
}
