/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author taisk
 */
public class CartItem {
    private int cartId;
    private int quantity;
    private Product product;
    private float total;

    public CartItem() {
    }

    public CartItem(int cartId, int quantity, Product product) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getTotal(ArrayList<CartItem> cartItems) {
        float price = 0;
        for (CartItem cartItem : cartItems) {
            price += cartItem.quantity * cartItem.product.getUnitPrice();
        }
        return price;
    }
    
}
