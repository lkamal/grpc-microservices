package com.digizol.grpc.java.ms.review;

import java.io.IOException;

public class ReviewApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        ReviewServer server = new ReviewServer(5050);
        server.start();
    }

}
