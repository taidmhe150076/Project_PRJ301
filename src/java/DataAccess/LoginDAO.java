/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author taisk
 */
public class LoginDAO extends DBContext {

    public Account getByUsernamePassword(String user, String pass) {
        try {
            String sql = "SELECT Username,Password,Role,CustomerID,Email FROM Login \n"
                    + "WHERE Username = ? AND Password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                account.setRole(rs.getString("Role"));
                account.setCustomerID(rs.getString("CustomerID"));
                account.setEmail(rs.getString("Email"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isAdmin(String user, String pass, String role) {
        try {
            String sql = "SELECT Username,Password,Role FROM Login \n"
                    + "WHERE Username = ? AND Password = ? AND Role = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            stm.setString(3, role);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isEmail(String email) {
        try {
            String sql = "SELECT Email\n"
                    + "FROM Login\n"
                    + "where Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int updatePassword(String email,String pass) {
        try {
            String sql = "UPDATE [dbo].[Login]\n"
                    + "   SET \n"
                    + "     [Password] = ?\n"
                    + " WHERE Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, email);
            int rs = stm.executeUpdate();
            if (rs > 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
