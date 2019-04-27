package com.gierasimiuk.jwtrest.model;

import java.util.ArrayDeque;

public class ProductResponse {
    private ArrayDeque<Product> products;
    private String message;
    public ProductResponse(ArrayDeque<Product> products, String message) {
        this.products = products;
        this.message = message;
    }
    public ArrayDeque<Product> getProducts() {
        return this.products;
    }
    public String getMessage() {
        return this.message;
    }
    public void setProducts(ArrayDeque<Product> products) {
        this.products = products;
    }
    public void setMessage(String message) { 
        this.message = message;
    }
}