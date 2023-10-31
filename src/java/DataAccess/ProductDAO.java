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
            sql = "SELECT p.ProductID\n"
                    + ",p.ProductName\n"
                    + ",p.SupplierID\n"
                    + ",p.CategoryID\n"
                    + ",p.QuantityPerUnit\n"
                    + ",p.UnitPrice\n"
                    + ",p.UnitsInStock\n"
                    + ",p.UnitsOnOrder\n"
                    + ",p.ReorderLevel\n"
                    + ",p.Discontinued\n"
                    + ",c.Image\n"
                    + "FROM Products as p inner join Categories as c\n"
                    + "on p.CategoryID = c.CategoryID\n"
                    + "ORDER BY p.ProductID ASC;";
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
                Category c = new Category();
                c.setImage(rs.getString("Image"));
                e.setCategory(c);
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
            sql = "SELECT p.ProductID\n"
                    + "      ,p.ProductName\n"
                    + "      ,p.SupplierID\n"
                    + "      ,p.CategoryID\n"
                    + "      ,p.QuantityPerUnit\n"
                    + "      ,p.UnitPrice\n"
                    + "      ,p.UnitsInStock\n"
                    + "      ,p.UnitsOnOrder\n"
                    + "      ,p.ReorderLevel\n"
                    + "      ,p.Discontinued\n"
                    + "      ,c.Image\n"
                    + "FROM Products as p inner join Categories as c\n"
                    + "on p.CategoryID = c.CategoryID\n"
                    + "where p.CategoryID = ?";
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
                Category c = new Category();
                c.setImage(rs.getString("Image"));
                e.setCategory(c);
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
            sql = "SELECT p.[ProductID]\n"
                    + "      ,p.[ProductName]\n"
                    + "      ,p.[SupplierID]\n"
                    + "      ,p.[CategoryID]\n"
                    + "      ,p.[QuantityPerUnit]\n"
                    + "      ,p.[UnitPrice]\n"
                    + "      ,p.[UnitsInStock]\n"
                    + "      ,p.[UnitsOnOrder]\n"
                    + "      ,p.[ReorderLevel]\n"
                    + "      ,p.[Discontinued]\n"
                    + "	  ,c.Image\n"
                    + "  FROM [Northwind].[dbo].[Products] as p\n"
                    + "  inner join Categories as c\n"
                    + "  on p.CategoryID = c.CategoryID\n"
                    + "  Where ProductName LIKE ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setCategoryID(rs.getInt("CategoryID"));
                Category c = new Category();
                c.setImage(rs.getString("Image"));
                e.setCategory(c);
                products.add(e);
            }
            if (!products.isEmpty()) {
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

    public ArrayList<Product> searchProductByPice(String from, String to) throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "SELECT p.[ProductID]\n"
                    + "      ,p.[ProductName]\n"
                    + "      ,p.[SupplierID]\n"
                    + "      ,p.[CategoryID]\n"
                    + "      ,p.[QuantityPerUnit]\n"
                    + "      ,p.[UnitPrice]\n"
                    + "      ,p.[UnitsInStock]\n"
                    + "      ,p.[UnitsOnOrder]\n"
                    + "      ,p.[ReorderLevel]\n"
                    + "      ,p.[Discontinued]\n"
                    + "	  ,c.Image\n"
                    + "  FROM [Northwind].[dbo].[Products] as p\n"
                    + "  inner join Categories as c\n"
                    + "  on p.CategoryID = c.CategoryID\n"
                    + "  Where p.UnitPrice >= ? and p.UnitPrice < ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(from));
            stm.setInt(2, Integer.parseInt(to));
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                e.setCategoryID(rs.getInt("CategoryID"));
                Category c = new Category();
                c.setImage(rs.getString("Image"));
                e.setCategory(c);
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

    public ArrayList<Product> getProductTopNew() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                    + "SELECT TOP (5) [ProductID]\n"
                    + "      ,p.[ProductName]\n"
                    + "      ,p.[SupplierID]\n"
                    + "      ,p.[CategoryID]\n"
                    + "      ,p.[QuantityPerUnit]\n"
                    + "      ,p.[UnitPrice]\n"
                    + "      ,p.[UnitsInStock]\n"
                    + "      ,p.[UnitsOnOrder]\n"
                    + "      ,p.[ReorderLevel]\n"
                    + "      ,p.[Discontinued]\n"
                    + "      ,c.[Image]\n"
                    + "  FROM [Northwind].[dbo].[Products] as p\n"
                    + "  inner join Categories as c\n"
                    + "  ON p.CategoryID = c.CategoryID\n"
                    + "  ORDER BY CreateAt DESC";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setProductID(rs.getInt("ProductID"));
                e.setCategoryID(rs.getInt("CategoryID"));
                e.setProductName(rs.getString("ProductName"));
                e.setUnitsInStock(rs.getFloat("UnitsInStock"));
                e.setUnitPrice(rs.getFloat("UnitPrice"));
                Category c = new Category();
                c.setImage(rs.getString("Image"));
                e.setCategory(c);
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

    public int updateProduct(Product product) throws Exception {
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "UPDATE [dbo].[Products]\n"
                    + "   SET [ProductName] = ?\n"
                    + "      ,[SupplierID] = ?\n"
                    + "      ,[CategoryID] = ?\n"
                    + "      ,[QuantityPerUnit] = ?\n"
                    + "      ,[UnitPrice] = ?\n"
                    + "      ,[UnitsInStock] = ?\n"
                    + "      ,[UnitsOnOrder] = ?\n"
                    + "      ,[ReorderLevel] = ?\n"
                    + "      ,[Discontinued] = ?\n"
                    + "      ,[CreateAt] = ?\n"
                    + " WHERE ProductID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getProductName());
            stm.setInt(2, 1);
            stm.setInt(3, product.getCategoryID());
            stm.setString(4, product.getQuantityPerUnit());
            stm.setFloat(5, product.getUnitPrice());
            stm.setInt(6, (int) product.getUnitsInStock());
            stm.setInt(7, (int) product.getUnitsOnOrder());
            stm.setInt(8, (int) product.getReorderLevel());
            stm.setBoolean(9, product.isDiscontinued());
            stm.setDate(10, product.getCreateAt());
            stm.setInt(11, product.getProductID());
            int result = stm.executeUpdate();

            if (result > 0) {
                return result;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return 0;
    }

    public int deleteProductByid(int productId) throws Exception {
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "DELETE FROM [dbo].[Products]\n"
                    + "      WHERE ProductID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            int result = stm.executeUpdate();
            if (result > 0) {
                return result;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return 0;
    }

    public int deleteProductDetail(int productId) throws Exception {
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "DELETE FROM [dbo].[Order Details]\n"
                    + "      WHERE ProductID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            int result = stm.executeUpdate();
            if (result > 0) {
                return result;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return 0;
    }

    public int createProduct(Product product) throws Exception {
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            sql = "INSERT INTO [dbo].[Products]\n"
                    + "           ([ProductName]\n"
                    + "           ,[SupplierID]\n"
                    + "           ,[CategoryID]\n"
                    + "           ,[QuantityPerUnit]\n"
                    + "           ,[UnitPrice]\n"
                    + "           ,[UnitsInStock]\n"
                    + "           ,[UnitsOnOrder]\n"
                    + "           ,[ReorderLevel]\n"
                    + "           ,[Discontinued]\n"
                    + "           ,[CreateAt])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,1\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,1\n"
                    + "           ,GETDATE())";
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getProductName());
            stm.setInt(2, product.getCategoryID());
            stm.setString(3, product.getQuantityPerUnit());
            stm.setFloat(4, product.getUnitPrice());
            stm.setFloat(5, product.getUnitsInStock());
            stm.setFloat(6, product.getUnitsOnOrder());
            stm.setFloat(7, product.getReorderLevel());
            int result = stm.executeUpdate();
            if (result > 0) {
                return result;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return 0;
    }
}
