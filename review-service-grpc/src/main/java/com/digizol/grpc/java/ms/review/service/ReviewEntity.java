package com.digizol.grpc.java.ms.review.service;

public class ReviewEntity {
    private int id;
    private String title;
    private String message;
    private int rating;

    public ReviewEntity(int id, String title, String message, int rating) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }
}
