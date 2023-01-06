package com.masai.main.service;

import com.masai.main.dto.CartDto;
import com.masai.main.dto.CartResDto;
import com.masai.main.entity.CartProduct;

import java.util.List;

public interface CartService {
    public CartResDto addToCart(CartDto dto);
    public List<CartProduct> getAllCartItems(int cartId);
    public CartResDto removeCartItem(CartDto dto);
    public CartResDto removeAllCartItems(int cartId);
    public CartResDto updateCartItemQuantity(CartDto dto);
}
