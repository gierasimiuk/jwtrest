package com.gierasimiuk.jwtrest.service;

import java.util.ArrayDeque;
import java.util.UUID;

import com.gierasimiuk.jwtrest.model.Product;

public class DummyDataService {
    private final ArrayDeque<Product> products;
    public DummyDataService() {
        this.products = new ArrayDeque<Product>();
        this.populate();
    }
    public ArrayDeque<Product> getProducts() {
        return this.products;
    }
    public void populate() {
        this.products.add(new Product(UUID.randomUUID().toString(), "Cheese", "$3.49"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Eggs", "$7.49"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Steak", "$9.99"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Banana", "$5.99/kg"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Orange", "$3.99/kg"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Apple", "$6.49/kg"));
        this.products.add(new Product(UUID.randomUUID().toString(), "Potato", "$3.99/kg"));
    }
}