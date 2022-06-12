package com.digizol.grpc.java.ms.product.service.grpc;

import com.digizol.grpc.java.ms.product.controller.ReviewData;
import com.digizol.grpc.java.ms.product.service.ReviewDataRepository;
import com.digizol.grpc.java.ms.review.grpc.ProductId;
import com.digizol.grpc.java.ms.review.grpc.Review;
import com.digizol.grpc.java.ms.review.grpc.ReviewServiceGrpc;
import com.digizol.grpc.java.ms.review.grpc.Reviews;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ReviewDataRepositoryGrpcImpl implements ReviewDataRepository {

    @Autowired
    private GrpcConfiguration grpcConfiguration;

    private ReviewServiceGrpc.ReviewServiceBlockingStub blockingStub;

    private Logger logger = LogManager.getLogger(ReviewDataRepositoryGrpcImpl.class);

    @PostConstruct
    public void init() {
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress(grpcConfiguration.getHost(), grpcConfiguration.getPort())
                        .usePlaintext()
                        .build();
        blockingStub = ReviewServiceGrpc.newBlockingStub(channel);
    }

    public ReviewData[] getReviewData(String productId) {
        final Reviews reviews;
        try {
            reviews = blockingStub.getReviews(
                    ProductId.newBuilder()
                            .setId(productId)
                            .build());
        } catch (Exception e) {
            logger.error("Error in retrieving data from ReviewData Grpc server, {}", e.getMessage());
            logger.debug("Exception", e);
            return new ReviewData[0];
        }

        final ReviewData[] reviewData =
                reviews.getReviewsList().stream()
                        .map(review -> toReviewData(review))
                        .toArray(size -> new ReviewData[size]);
        logger.info("ReviewData Grpc server returned [{}] results for Product with id [{}]",
                reviewData.length, productId);
        return reviewData;
    }

    private ReviewData toReviewData(Review review) {
        return new ReviewData(review.getId(),
                review.getTitle(),
                review.getText(),
                review.getRating());
    }
}
