package com.digizol.grpc.java.ms.product.controller;

public class ReviewData {
    private long id;
    private String title;
    private String message;
    private int rating;

    public ReviewData(long id, String title, String message, int rating) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.rating = rating;
    }

    public long getId() {
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

    @Override
    public String toString() {
        return "ReviewData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", rating=" + rating +
                '}';
    }
}
