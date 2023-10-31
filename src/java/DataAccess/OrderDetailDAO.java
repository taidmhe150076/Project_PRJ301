/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import Models.CartItem;
import Models.OrderDetails;
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
public class OrderDetailDAO extends DBContext {

    public ArrayList<OrderDetails> getlistOrderDetailses(int year, int month) {
        ArrayList<OrderDetails> listOrderDetailses = new ArrayList<>();
        try {
            String sql = "SELECT od.OrderID\n"
                    + "      ,od.UnitPrice\n"
                    + "      ,od.Quantity\n"
                    + "  FROM Orders as o\n"
                    + "  inner join [Order Details] as od\n"
                    + "  on o.OrderID = od.OrderID\n"
                    + "  WHERE DATEPART(YEAR, OrderDate) = ? and DATEPART(MONTH, OrderDate) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, year);
            stm.setInt(2, month);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderID(rs.getInt("OrderID"));
                orderDetails.setUnitPrice(rs.getFloat("UnitPrice"));
                orderDetails.setQuantity(rs.getInt("Quantity"));
                listOrderDetailses.add(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderDetailses;
    }

    public ArrayList<OrderDetails> getlistOrderDetailsesToday(int year, int month, int day) {
        ArrayList<OrderDetails> listOrderDetailses = new ArrayList<>();
        try {
            String sql = "SELECT od.OrderID\n"
                    + "      ,od.UnitPrice\n"
                    + "      ,od.Quantity\n"
                    + "  FROM Orders as o\n"
                    + "  inner join [Order Details] as od\n"
                    + "  on o.OrderID = od.OrderID\n"
                    + "  WHERE DATEPART(YEAR, OrderDate) = ? and DATEPART(MONTH, OrderDate) = ? and DATEPART(DAY, OrderDate) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, year);
            stm.setInt(2, month);
            stm.setInt(3, day);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderID(rs.getInt("OrderID"));
                orderDetails.setUnitPrice(rs.getFloat("UnitPrice"));
                orderDetails.setQuantity(rs.getInt("Quantity"));
                listOrderDetailses.add(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderDetailses;
    }

    public ArrayList<OrderDetails> getlistOrderDetailsesALL() {
        ArrayList<OrderDetails> listOrderDetailses = new ArrayList<>();
        try {
            String sql = "SELECT od.OrderID\n"
                    + "      ,od.UnitPrice\n"
                    + "      ,od.Quantity\n"
                    + "  FROM Orders as o\n"
                    + "  inner join [Order Details] as od\n"
                    + "  on o.OrderID = od.OrderID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderID(rs.getInt("OrderID"));
                orderDetails.setUnitPrice(rs.getFloat("UnitPrice"));
                orderDetails.setQuantity(rs.getInt("Quantity"));
                listOrderDetailses.add(orderDetails);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderDetailses;
    }

    public ArrayList<OrderDetails> getOrderDetailByOrderID(int orderID) {
        ArrayList<OrderDetails> listOrderDetailses = new ArrayList<>();
        try {
            String sql = "SELECT OrderID\n"
                    + "      ,ProductID\n"
                    + "      ,UnitPrice\n"
                    + "      ,Quantity\n"
                    + "      ,Discount\n"
                    + "  FROM [Order Details]\n"
                    + "  WHERE OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderID(rs.getInt("OrderID"));
                orderDetails.setProductID(rs.getInt("ProductID"));
                orderDetails.setUnitPrice(rs.getFloat("UnitPrice"));
                orderDetails.setQuantity(rs.getInt("Quantity"));
                listOrderDetailses.add(orderDetails);
            }
            if (!listOrderDetailses.isEmpty()) {
                return listOrderDetailses;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int createOrderDetail(CartItem cartItem, int orderID) {
        try {
            String sql = "INSERT INTO [Order Details]\n"
                    + "           ([OrderID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[UnitPrice]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Discount])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,null)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderID);
            stm.setInt(2, cartItem.getProduct().getProductID());
            stm.setFloat(3, cartItem.getProduct().getUnitPrice());
            stm.setInt(4, cartItem.getQuantity());
            int result = stm.executeUpdate();
            if (result > 0) {
               return result ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
