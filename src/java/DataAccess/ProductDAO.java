/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Category;
import Models.Product;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author taisk
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getAllProduct() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "SELECT [ProductID]\n"
                    + ",[ProductName]\n"
                    + ",[SupplierID]\n"
                    + ",[CategoryID]\n"
                    + ",[QuantityPerUnit]\n"
                    + ",[UnitPrice]\n"
                    + ",[UnitsInStock]\n"
                    + ",[UnitsOnOrder]\n"
                    + ",[ReorderLevel]\n"
                    + ",[Discontinued]\n"
                    + "  FROM [dbo].[Products]";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setCategoryID(rs.getInt("CategoryID"));
                products.add(e);
            }
            if (products != null) {
                return products;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return null;
    }
    public ArrayList<Product> getProductByCategoryID(String Id) throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "SELECT [ProductID]\n"
                    + ",[ProductName]\n"
                    + ",[SupplierID]\n"
                    + ",[CategoryID]\n"
                    + ",[QuantityPerUnit]\n"
                    + ",[UnitPrice]\n"
                    + ",[UnitsInStock]\n"
                    + ",[UnitsOnOrder]\n"
                    + ",[ReorderLevel]\n"
                    + ",[Discontinued]\n"
                    + "  FROM [dbo].[Products]\n"
                    + "Where CategoryID = ? ";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(Id));
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setCategoryID(rs.getInt("CategoryID"));
                products.add(e);
            }
            if (products != null) {
                return products;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return null;
    }
    public ArrayList<Product> getProductByName(String name) throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "SELECT [ProductID]\n"
                    + ",[ProductName]\n"
                    + ",[SupplierID]\n"
                    + ",[CategoryID]\n"
                    + ",[QuantityPerUnit]\n"
                    + ",[UnitPrice]\n"
                    + ",[UnitsInStock]\n"
                    + ",[UnitsOnOrder]\n"
                    + ",[ReorderLevel]\n"
                    + ",[Discontinued]\n"
                    + "  FROM [dbo].[Products]\n"
                    + "Where ProductName LIKE ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name+ "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setCategoryID(rs.getInt("CategoryID"));
                products.add(e);
            }
            if (products.size() > 0) {
                return products;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return null;
    }
    public Product getProductById(int id) throws Exception {
        Product e = new Product();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "SELECT p.ProductID, p.ProductName, p.UnitPrice, p.UnitsInStock, p.QuantityPerUnit, c.CategoryName, c.Description, p.CategoryID\n"
                    + "	FROM [Northwind].[dbo].[Products] as p\n"
                    + "	inner join [Northwind].[dbo].Categories as c\n"
                    + "	on p.CategoryID = c.CategoryID\n"
                    + "	where p.ProductID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                e.setProductID(rs.getInt("ProductID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
                Category c = new Category();
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setCategory(c);
            }
            return e;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
