/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author taisk
 */
public class OrderDetails {
    private int orderID;
    private int productID;
    private float unitPrice;
    private int quantity;
    private float revenue;
    
    private Orders orders;

    public OrderDetails() {
    }

    public OrderDetails(int orderID, int productID, float unitPrice, int quantity, Orders orders) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.orders = orders;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public float getRevenue() {
        return this.quantity * this.unitPrice;
    }
    
    
}
