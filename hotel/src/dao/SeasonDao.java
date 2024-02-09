package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }
    public ArrayList<Season> findAll(){//season veri tabanından verileri çekeriz.
        ArrayList<Season> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season ORDER BY id ASC";
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

    public Season match(ResultSet rs) throws SQLException {//season bilgilerini eşleme
        Season object = new Season();
        object = new Season();
        object.setId(rs.getInt("id"));
        object.setHotel_id(rs.getInt("hotel_id"));
        object.setStart_date(LocalDate.parse(rs.getString("start_date")));
        object.setFinish_date(LocalDate.parse(rs.getString("finish_date")));

        return object;
    }
    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
    /*public Season getById(int id){
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ? ";
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
    public ArrayList<Season> getById(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
    public boolean save(Season season){
        String sql="INSERT INTO public.hotel_season (hotel_id,start_date,finish_date) VALUES(?,?,?)";
        try{
            PreparedStatement pr=this.connection.prepareStatement(sql);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
