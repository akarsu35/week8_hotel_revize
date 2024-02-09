package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;

    public PensionDao() {
        this.connection = Db.getInstance();
    }
    public ArrayList<Pension> findAll(){//pansion veri tabanından verileri çekeriz.
        ArrayList<Pension> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pansion ORDER BY id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()){
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public Pension match(ResultSet rs) throws SQLException {//hotel bilgilerini eşleme
        Pension object = new Pension();
        object = new Pension();
        object.setId(rs.getInt("id"));
        object.setHotel_id(rs.getInt("hotel_id"));
        object.setPension_type(rs.getString("pansion_type"));

        return object;
    }
    public ArrayList<Pension> getPansionByOtelId(int otelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_pansion WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }
    /*public Pension getById(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.hotel_pansion WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }*/
    public ArrayList<Pension> getById(int otelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_pansion WHERE id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }




    public boolean save(Pension pension){
        String sql="INSERT INTO public.hotel_pansion (hotel_id,pansion_type) VALUES(?,?)";
        try{
            PreparedStatement pr=this.connection.prepareStatement(sql);
            //pr.setInt(1, pension.getId());
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type());
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
