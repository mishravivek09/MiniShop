package com.masai.main.service;

import com.masai.main.dto.CartDto;
import com.masai.main.dto.CartResDto;
import com.masai.main.entity.Cart;
import com.masai.main.entity.CartProduct;
import com.masai.main.exception.MyRuntimeExceptions;
import com.masai.main.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository repo;


    @Override
    public CartResDto addToCart(CartDto dto) {
        Optional<Cart> opt=repo.findById(dto.getCartId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Cart id is invalid..");
        }

        Cart cart=opt.get();

        List<CartProduct> list=cart.getProducts();
        list.forEach(p->{
            if(p.getProductId() == dto.getProductId()){
                throw new MyRuntimeExceptions("Product already exist in cart..");
            }
        });

        CartProduct cp=new CartProduct();
        cp.setProductId(dto.getProductId());
        cp.setCategory(dto.getCategory());
        cp.setImage(dto.getImage());
        cp.setPrice(dto.getPrice());
        cp.setQuantity(dto.getQuantity());
        cp.setProductName(dto.getProductName());
        cp.setRating(dto.getRating());
        list.add(cp);

        repo.save(cart);

        CartResDto res=new CartResDto();
        res.setDateTime(LocalDateTime.now());
        res.setMessage("Product added to cart..");

        return res;
    }

    @Override
    public List<CartProduct> getAllCartItems(int cartId) {
        Optional<Cart> opt=repo.findById(cartId);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid cart id..");
        }
        Cart cart=opt.get();
        List<CartProduct> list=cart.getProducts();
        if(list.isEmpty()){
            throw new MyRuntimeExceptions("Cart is empty..");
        }
        return list;
    }

    @Override
    public CartResDto removeCartItem(CartDto dto) {
        Optional<Cart> opt=repo.findById(dto.getCartId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid cart id..");
        }
        Cart cart=opt.get();

        List<CartProduct> list=cart.getProducts();

        CartProduct cp=new CartProduct();
        cp.setProductId(dto.getProductId());
        cp.setCategory(dto.getCategory());
        cp.setImage(dto.getImage());
        cp.setPrice(dto.getPrice());
        cp.setQuantity(dto.getQuantity());
        cp.setProductName(dto.getProductName());
        cp.setRating(dto.getRating());

        if(list.contains(cp)){
            list.remove(cp);
        }else{
            throw new MyRuntimeExceptions("Product doesn't exist in cart with id : "+dto.getProductId());
        }

//        list.forEach(p->{
//            if(!p.equals(cp)){
//                throw new MyRuntimeExceptions("Product doesn't exist in cart with id : "+dto.getProductId());
//            }else{
//                list.remove(cp);
//            }
//        });

        repo.save(cart);

        CartResDto res=new CartResDto();
        res.setDateTime(LocalDateTime.now());
        res.setMessage("Product removed successfully !");

        return res;
    }

    @Override
    public CartResDto removeAllCartItems(int cartId) {
        Optional<Cart> opt=repo.findById(cartId);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid cart id..");
        }
        Cart cart=opt.get();
        if(cart.getProducts().isEmpty()){
            throw new MyRuntimeExceptions("Cart is already empty..");
        }
        cart.getProducts().clear();
        repo.save(cart);

        CartResDto res=new CartResDto();
        res.setDateTime(LocalDateTime.now());
        res.setMessage("Cart is empty..");

        return res;
    }

    @Override
    public CartResDto updateCartItemQuantity(CartDto dto) {
        Optional<Cart> opt=repo.findById(dto.getCartId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Cart id is invalid..");
        }

        Cart cart=opt.get();

        List<CartProduct> list=cart.getProducts();


        for(CartProduct p:list){
            if(p.getProductId()==dto.getProductId()){
                CartProduct cp=p;
                cp.setProductId(dto.getProductId());
                cp.setCategory(dto.getCategory());
                cp.setImage(dto.getImage());
                cp.setPrice(dto.getPrice());
                cp.setQuantity(dto.getQuantity());
                cp.setProductName(dto.getProductName());
                cp.setRating(dto.getRating());
            }
        }

        repo.save(cart);

        CartResDto res=new CartResDto();
        res.setDateTime(LocalDateTime.now());
        res.setMessage("Product updated successfully..");

        return res;
    }
}
