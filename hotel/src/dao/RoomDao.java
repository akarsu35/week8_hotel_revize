package dao;

import core.Db;
import entity.Hotel;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection connection;

    public RoomDao() {
        this.connection = Db.getInstance();

    }
    public ArrayList<Room> findAll(){//room veri tabanından verileri çekeriz.
        ArrayList<Room> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.room ORDER BY id ASC";
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
    public Room match(ResultSet rs) throws SQLException {//hotel bilgilerini eşleme
        Room object = new Room();
        object = new Room();
        object.setId(rs.getInt("id"));
        object.setHotel_id(rs.getInt("hotel_id"));
        object.setPension_id(rs.getInt("pension_id"));
        object.setSeason_id(rs.getInt("season_id"));
        object.setType(rs.getString("type"));
        object.setStock(rs.getInt("stock"));
        object.setAdult_price(rs.getDouble("adult_price"));
        object.setChild_price(rs.getDouble("child_price"));
        object.setBed_capacity(rs.getInt("bed_capacity"));
        object.setSquer_meter(rs.getInt("squer_meter"));
        object.setTelevision(rs.getBoolean("television"));
        return object;
    }
    public Room getById(int id){
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ? ";
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
    }

    //Todo oda sayısını azaltma
    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET stock = ? WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2,room.getId());

            pr.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean save(Room room){
        String query="INSERT INTO public.room"+
                "("+
                "hotel_id,pension_id,season_id,type,adult_price," +
                "child_price,bed_capacity,squer_meter,television," +
                "minibar,game_console,cash_box,projection,stock)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr=this.connection.prepareStatement(query);
            pr.setInt(1,room.getHotel_id());
            pr.setInt(2,room.getPension_id());
            pr.setInt(3,room.getSeason_id());
            pr.setString(4,room.getType());
            pr.setDouble(5,room.getAdult_price());
            pr.setDouble(6,room.getChild_price());
            pr.setInt(7,room.getBed_capacity());
            pr.setInt(8,room.getSquer_meter());
            pr.setBoolean(9,room.isTelevision());
            pr.setBoolean(10,room.isMinibar());
            pr.setBoolean(11,room.isGame_console());
            pr.setBoolean(12,room.isCash_box());
            pr.setBoolean(13, room.isProjection());
            pr.setInt(14,room.getStock());
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
}
