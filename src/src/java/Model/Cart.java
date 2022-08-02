/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author hellb
 */
public class Cart {

    private int cartID;
    private User userId;
    private  Map<Integer, Cart_Detail> carts = new LinkedHashMap<>() ;
    public Cart() {
    }

    public Cart(User userId) {
        this.userId = userId;
    }
    
    

    @Override
    public String toString() {
        return "Cart{" + "cartID=" + cartID + ", userId=" + userId + ", carts=" + carts + '}';
    }

    

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Map<Integer, Cart_Detail> getCarts() {
        return carts;
    }

    public void setCarts(Map<Integer, Cart_Detail> carts) {
        this.carts = carts;
    }


}
