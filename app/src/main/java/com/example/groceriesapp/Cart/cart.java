package com.example.groceriesapp.Cart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cart_Table")
public class cart{
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private Double price;
    @SerializedName("image")
    private String image;
    public cart(Integer id, String title, Double price, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
