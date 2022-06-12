package com.digizol.grpc.java.ms.review.service;

import java.util.HashMap;
import java.util.Map;

public class ReviewService {

    private int count = 1;

    private Map<String, ReviewEntity[]> productReviews = new HashMap<>();

    public ReviewService() {
        createReviewsForProd1("prod-1");
        createReviewsForProd2("prod-2");
    }

    public ReviewEntity[] getReview(String productId) {
        final ReviewEntity[] reviewEntities = productReviews.get(productId);

        if (reviewEntities == null) {
            return new ReviewEntity[0];
        }
        return reviewEntities;
    }

    private void createReviewsForProd1(String productId) {
        ReviewEntity[] reviewArray = new ReviewEntity[3];

        int i = 0;
        {
            ReviewEntity reviewData = new ReviewEntity(count++, "iPhone - Amazing", "The best product I have ever used", 5);
            reviewArray[i] = reviewData;
            i++;
        }
        {
            ReviewEntity reviewData = new ReviewEntity(count++, "iPhone - Not worth", "Too expensive, though it is feature rich", 3);
            reviewArray[i] = reviewData;
            i++;
        }
        {
            ReviewEntity reviewData = new ReviewEntity(count++, "Worst iPhone", "Apple company's worst product, don't buy", 1);
            reviewArray[i] = reviewData;
            i++;
        }
        productReviews.put(productId, reviewArray);
    }

    private void createReviewsForProd2(String productId) {
        ReviewEntity[] reviewArray = new ReviewEntity[2];

        int i = 0;
        {
            ReviewEntity reviewData = new ReviewEntity(count++, "Samsung - Amazing", "The best product I have ever used", 5);
            reviewArray[i] = reviewData;
            i++;
        }
        {
            ReviewEntity reviewData = new ReviewEntity(count++, "Samsung - Not worth", "Useless phone, better buy an iPhone", 2);
            reviewArray[i] = reviewData;
            i++;
        }
        productReviews.put(productId, reviewArray);
    }

}
