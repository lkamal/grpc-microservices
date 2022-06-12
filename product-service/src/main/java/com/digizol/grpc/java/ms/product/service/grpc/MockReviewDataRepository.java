package com.digizol.grpc.java.ms.product.service.grpc;

import com.digizol.grpc.java.ms.product.controller.ReviewData;
import com.digizol.grpc.java.ms.product.service.ReviewDataRepository;

public class MockReviewDataRepository implements ReviewDataRepository {

    public ReviewData[] getReviewData(String productId) {
        ReviewData[] reviewDataArray = new ReviewData[3];

        int i = 0;
        {
            ReviewData reviewData = new ReviewData(1,"Amazing", "The best product I have ever used", 5);
            reviewDataArray[i] = reviewData;
            i++;
        }
        {
            ReviewData reviewData = new ReviewData(2,"Not worth", "Too expensive, though it is feature rich", 3);
            reviewDataArray[i] = reviewData;
            i++;
        }
        {
            ReviewData reviewData = new ReviewData(3,"Worst iPhone", "Apple company's worst product, don't buy", 1);
            reviewDataArray[i] = reviewData;
            i++;
        }
        return reviewDataArray;
    }
}
