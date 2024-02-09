package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao {
    private final Connection con;
    public EmployeeDao() {
        this.con = Db.getInstance();
    }
    public ArrayList<User> findAll(){//User listesini veritabanından alan SQL sorgu metodu
        ArrayList<User> userList=new ArrayList<>();
        String sql="SELECT * FROM public.hotel";
        try {
            ResultSet rs=this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));

            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        return userList;
    }
    public User match(ResultSet rs) throws SQLException {//veri tabanındaki tabloda bulunan sütunların verilerini atama yapar.(set leme yapar yani)
        User obj=new User();
        obj.setId(rs.getInt("id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(rs.getString("user_role"));
        return obj;

    }
    public User getById(int id){
        User obj=null;
        String query="SELECT * FROM public.hotel WHERE id=?";
        try{
            PreparedStatement pr=this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                obj=this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;

    }


}
