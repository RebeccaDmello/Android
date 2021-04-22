package com.example.curbsidethairestaurant;

public class FoodList {
    String name, description, price;

    public FoodList(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public FoodList(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
