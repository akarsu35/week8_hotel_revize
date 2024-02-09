package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection connection;

    public HotelDao() {

        this.connection = Db.getInstance();
    }
    public ArrayList<Hotel> findAll(){//hotel veri tabanından verileri çekeriz.
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel ORDER BY id ASC";
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
    public Hotel match(ResultSet rs) throws SQLException {//hotel bilgilerini eşleme
        Hotel object = new Hotel();
        object = new Hotel();
        object.setId(rs.getInt("id"));
        object.setName(rs.getString("name"));
        object.setAdress(rs.getString("address"));
        object.setMail(rs.getString("mail"));
        object.setPhone(rs.getString("phone"));
        object.setStar(rs.getString("star"));
        object.setCar_park(rs.getBoolean("car_park"));
        object.setWifi(rs.getBoolean("wifi"));
        object.setFitness(rs.getBoolean("fitness"));
        object.setConcierge(rs.getBoolean("concierge"));
        object.setSpa(rs.getBoolean("spa"));
        object.setRoomservice(rs.getBoolean("room_service"));
        return object;
    }
    public Hotel getById( int id){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ? ";
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
    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotel "+
                "(" +
                "name,"+
                "mail," +
                "phone,"+
                "address,"+
                "star,"+
                "car_park,"+
                "wifi,"+
                "pool,"+
                "fitness,"+
                "concierge,"+
                "spa,"+
                "room_service "+
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1,hotel.getName());
            pr.setString(2,hotel.getMail());
            pr.setString(3,hotel.getPhone());
            pr.setString(4,hotel.getAdress());
            pr.setString(5,hotel.getStar());
            pr.setBoolean(6,hotel.isCar_park());
            pr.setBoolean(7,hotel.isWifi());
            pr.setBoolean(8,hotel.isPool());
            pr.setBoolean(9,hotel.isFitness());
            pr.setBoolean(10,hotel.isConcierge());
            pr.setBoolean(11,hotel.isSpa());
            pr.setBoolean(12,hotel.isRoomservice());
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}
