/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author House
 */
public class Product {
    private int id;
    private String name;
    private float price;
    private int category;

    public Product(int id, String name, float price, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product( String name, float price, int category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "product{" + "id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + '}';
    }

   
    
}
