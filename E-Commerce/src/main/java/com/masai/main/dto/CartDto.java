package com.masai.main.dto;

import com.masai.main.myenum.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    @Min(value = 1,message = "Cart id should not be empty..")
    private int cartId;
    private int productId;
    private String productName;
    private String image;
    private long quantity;
    private double price;
    private Categories category;
    private double rating;
}
