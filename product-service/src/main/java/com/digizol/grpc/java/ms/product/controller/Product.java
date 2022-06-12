package com.digizol.grpc.java.ms.product.controller;

import java.util.Arrays;

public class Product {
    private String id;
    private String name;
    private float price;
    private ReviewData[] reviews;

    public Product(){
    }

    public Product(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public ReviewData[] getReviews() {
        return reviews;
    }

    public void setReviews(ReviewData[] reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", reviews=" + Arrays.toString(reviews) +
                '}';
    }
}
