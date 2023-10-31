/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Customer;
import Models.OrderDetails;
import Models.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taisk
 */
public class OrdersDAO extends DBContext {

    public int getCountOrder() {
        try {
            String sql = "SELECT COUNT(OrderID) FROM Orders";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Orders> getOrder() {
        ArrayList<Orders> orderses = new ArrayList<>();
        try {
            String sql = "SELECT o.OrderID\n"
                    + "      ,o.OrderDate\n"
                    + "	  ,c.ContactName\n"
                    + "	  ,o.Status\n"
                    + "  FROM [Orders] as o\n"
                    + "  inner join Customers as c\n"
                    + "  on o.CustomerID = c.CustomerID"
                    + "  ORDER BY o.OrderDate DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderID(rs.getInt("OrderID"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setStatus(rs.getBoolean("Status"));
                Customer c = new Customer();
                c.setContactName(rs.getString("ContactName"));

                o.setCustomer(c);
                orderses.add(o);
            }

            if (orderses.size() > 0) {
                return orderses;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Orders getOrderTop1() {
        Orders orderses = new Orders();
        try {
            String sql = "SELECT Top(1) o.OrderID\n"
                    + "      ,o.OrderDate\n"
                    + "	  ,c.ContactName\n"
                    + "  FROM [Orders] as o\n"
                    + "  inner join Customers as c\n"
                    + "  on o.CustomerID = c.CustomerID"
                    + "  ORDER BY o.OrderDate DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                orderses.setOrderID(rs.getInt("OrderID"));
                orderses.setOrderDate(rs.getDate("OrderDate"));

                Customer c = new Customer();
                c.setContactName(rs.getString("ContactName"));

                orderses.setCustomer(c);
            }

            if (orderses != null) {
                return orderses;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Orders getOrderById(int Id) {
        Orders o = new Orders();
        try {
            String sql = "SELECT o.OrderID\n"
                    + "      ,o.CustomerID\n"
                    + "      ,o.OrderDate\n"
                    + "      ,o.RequiredDate\n"
                    + "      ,o.ShippedDate\n"
                    + "      ,o.ShipVia\n"
                    + "      ,o.Freight\n"
                    + "      ,o.ShipName\n"
                    + "      ,o.ShipAddress\n"
                    + "      ,o.ShipCity\n"
                    + "      ,o.ShipRegion\n"
                    + "      ,o.ShipPostalCode\n"
                    + "      ,o.ShipCountry\n"
                    + "      ,c.CompanyName\n"
                    + "      ,c.ContactName\n"
                    + "      ,c.ContactTitle\n"
                    + "      ,c.Address\n"
                    + "      ,c.City\n"
                    + "      ,c.Region\n"
                    + "      ,c.PostalCode\n"
                    + "      ,c.Country\n"
                    + "      ,c.Phone\n"
                    + "      ,c.Fax\n"
                    + "  FROM [Orders] as o\n"
                    + "  inner join Customers as c\n"
                    + "  on o.CustomerID = c.CustomerID\n"
                    + "  where o.OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                o.setOrderID(rs.getInt("OrderID"));
                o.setCustomerID(rs.getString("CustomerID"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setRequiredDate(rs.getDate("RequiredDate"));
                o.setShippedDate(rs.getDate("ShippedDate"));
                o.setShipVia(rs.getInt("ShipVia"));
                o.setShipName(rs.getString("ShipName"));
                o.setShipAddress(rs.getString("ShipAddress"));
                o.setShipCity(rs.getString("ShipCity"));
                o.setShipRegion(rs.getString("ShipRegion"));
                o.setShipPostalCode(rs.getString("ShipPostalCode"));
                o.setShipCountry(rs.getString("ShipCountry"));

                Customer c = new Customer();
                c.setContactName(rs.getString("ContactName"));
                c.setCompanyName(rs.getString("CompanyName"));
                c.setContactTitle(rs.getString("ContactTitle"));
                c.setAddress(rs.getString("Address"));
                c.setCity(rs.getString("City"));
                c.setRegion(rs.getString("Region"));
                c.setPostalCode(rs.getString("PostalCode"));
                c.setCountry(rs.getString("Country"));
                c.setPhone(rs.getString("Phone"));
                c.setFax(rs.getString("Fax"));

                o.setCustomer(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    public int creatOrder(String cusId) {
        try {
            String sql = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([CustomerID]\n"
                    + "           ,[EmployeeID]\n"
                    + "           ,[OrderDate]\n"
                    + "           ,[RequiredDate]\n"
                    + "           ,[ShippedDate]\n"
                    + "           ,[ShipVia]\n"
                    + "           ,[Freight]\n"
                    + "           ,[ShipName]\n"
                    + "           ,[ShipAddress]\n"
                    + "           ,[ShipCity]\n"
                    + "           ,[ShipRegion]\n"
                    + "           ,[ShipPostalCode]\n"
                    + "           ,[ShipCountry])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,null\n"
                    + "           ,GETDATE()\n"
                    + "           ,GETDATE()\n"
                    + "           ,GETDATE()\n"
                    + "           ,null\n"
                    + "           ,null\n"
                    + "           ,'Vins et alcools Chevalier'\n"
                    + "           ,'59 rue de Abbaye'\n"
                    + "           ,'Reims'\n"
                    + "           ,null\n"
                    + "           ,51100\n"
                    + "           ,'France')";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cusId);
            int result = stm.executeUpdate();
            if (result != 0) {
                return result;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateOrderAccept(int orderId) {
        try {
            String sql = "UPDATE Orders SET Status = 1 where OrderID = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            int result = stm.executeUpdate();
            if (result != 0) {
                return result;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Orders> getOrder(String cusId) {
        ArrayList<Orders> orderses = new ArrayList<>();
        try {
            String sql = "SELECT o.OrderID\n"
                    + "    ,o.OrderDate\n"
                    + "    ,c.ContactName\n"
                    + "    ,o.Status\n"
                    + "FROM [Orders] as o\n"
                    + "inner join Customers as c\n"
                    + "on o.CustomerID = c.CustomerID\n"
                    + "where o.CustomerID = ?\n"
                    + "ORDER BY o.OrderDate DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cusId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Orders o = new Orders();
                o.setOrderID(rs.getInt("OrderID"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setStatus(rs.getBoolean("Status"));
                Customer c = new Customer();
                c.setContactName(rs.getString("ContactName"));

                o.setCustomer(c);
                orderses.add(o);
            }

            if (orderses.size() > 0) {
                return orderses;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
