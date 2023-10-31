/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import Models.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author taisk
 */
public class CategoryDAO extends DBContext {
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            sql = "SELECT [CategoryID]\n"
                    + ",[CategoryName]\n"
                    + ",[Description]\n"
                    + ",[Image]\n"
                    + "  FROM Categories";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {                
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setImage(rs.getString("Image"));
                categories.add(c);
            }
            if (categories != null) {
                return categories;
            }
        } catch (Exception ex) {
        }
        return  null;
    }
}
