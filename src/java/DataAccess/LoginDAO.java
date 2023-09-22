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
public class LoginDAO extends DBContext
{
    public Account getByUsernamePassword(String user,String pass)
    {
        try {
            String sql = "SELECT Username,Password,Role FROM Login \n" +
                    "WHERE Username = ? AND Password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                Account account = new Account();
                account.setUserName(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                account.setRole(rs.getString("Role"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
