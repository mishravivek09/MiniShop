package com.masai.main.entity;

import com.masai.main.myenum.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartProduct {
    private int productId;
    private String productName;
    private String image;
    private long quantity;
    private double price;
    private Categories category;
    private double rating;
}
