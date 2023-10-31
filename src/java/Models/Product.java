/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author taisk
 */
public class Product {
    private int productID;
    private String productName;
    private int supplierID;
    private int categoryID;
    private String quantityPerUnit;
    private float unitPrice;
    private float unitsInStock;
    private float unitsOnOrder;
    private float reorderLevel;
    private boolean discontinued;
    private Category category;
    private Date createAt;
    public Product() {
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Product(int productID, String productName, int supplierID, int categoryID, String quantityPerUnit, float unitPrice, float unitsInStock, float unitsOnOrder, float reorderLevel, boolean discontinued, Category category) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
        this.category = category;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(float unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public float getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(float unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public float getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(float reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
