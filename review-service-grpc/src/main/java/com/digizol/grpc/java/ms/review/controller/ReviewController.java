package com.digizol.grpc.java.ms.review.controller;

import com.digizol.grpc.java.ms.review.grpc.ProductId;
import com.digizol.grpc.java.ms.review.grpc.Review;
import com.digizol.grpc.java.ms.review.grpc.ReviewServiceGrpc;
import com.digizol.grpc.java.ms.review.grpc.Reviews;
import com.digizol.grpc.java.ms.review.service.ReviewEntity;
import com.digizol.grpc.java.ms.review.service.ReviewService;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewController extends ReviewServiceGrpc.ReviewServiceImplBase {

    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    @Override
    public void getReviews(ProductId productId, StreamObserver<Reviews> responseObserver) {

        final ReviewEntity[] reviews = reviewService.getReview(productId.getId());

        List<Review> reviewList =
                Arrays.stream(reviews)
                        .map(reviewEntity -> toReview(reviewEntity))
                        .collect(Collectors.toList());

        responseObserver.onNext(Reviews.newBuilder().addAllReviews(reviewList).build());
        responseObserver.onCompleted();
    }

    private Review toReview(ReviewEntity review) {
        return Review.newBuilder()
                .setId(review.getId())
                .setTitle(review.getTitle())
                .setText(review.getMessage())
                .setRating(review.getRating())
                .build();
    }
}
