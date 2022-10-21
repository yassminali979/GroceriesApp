
package com.example.groceriesapp;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Product implements Comparable<Product>{
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private Double price;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("image")
    private String image;
    @SerializedName("rating")
    private Rating rating;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(Product o) {
        return (int) (this.price - o.getPrice());
    }
    public static Comparator<Product> productComparator=new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };
}
