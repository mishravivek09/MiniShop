package com.masai.main.controller;

import com.masai.main.dto.CartDto;
import com.masai.main.dto.CartResDto;
import com.masai.main.entity.CartProduct;
import com.masai.main.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;

    @PostMapping("/add")
    public ResponseEntity<CartResDto> addItemToCart(@Valid @RequestBody CartDto dto){
        return new ResponseEntity<>(service.addToCart(dto), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<CartResDto> updateCartItemQuantityHandler(@Valid @RequestBody CartDto dto){
        return new ResponseEntity<>(service.updateCartItemQuantity(dto), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<List<CartProduct>> getAllCartItems(@RequestParam int cartId){
        return new ResponseEntity<>(service.getAllCartItems(cartId),HttpStatus.OK);
    }
    @PutMapping ("/remove")
    public ResponseEntity<CartResDto> removeProductFromCart(@Valid @RequestBody CartDto dto){
        return new ResponseEntity<>(service.removeCartItem(dto),HttpStatus.OK);
    }
    @GetMapping ("/remove/all/{cartId}")
    public ResponseEntity<CartResDto> deleteAllCartItem(@PathVariable int cartId){
        return new ResponseEntity<>(service.removeAllCartItems(cartId),HttpStatus.OK);
    }
}
