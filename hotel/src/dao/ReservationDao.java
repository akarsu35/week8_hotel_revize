package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Reservation;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection connection;

    public ReservationDao () {

        this.connection = Db.getInstance();
    }
    public ArrayList<Reservation> findAll(){//reservation veri tabanından verileri çekeriz.
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation ORDER BY id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()){
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }
    public Reservation match(ResultSet rs) throws SQLException {//reservasyon bilgilerini eşleme
        Reservation object = new Reservation();
        object = new Reservation();
        object.setId(rs.getInt("id"));
        object.setRoom_id(rs.getInt("room_id"));

        object.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        object.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        object.setTotal_price(rs.getInt("total_price"));
        object.setQuest_count(rs.getInt("quest_count"));
        object.setQuest_name(rs.getString("quest_name"));
        object.setQuest_identy(rs.getString("quest_identy"));
        object.setQuest_mail(rs.getString("quest_mail"));
        object.setQuest_phone(rs.getString("quest_phone"));
        return object;
    }
    public Reservation getById( int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ? ";
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
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation "+
                "(" +
                "quest_name,"+
                "quest_mail," +
                "quest_phone,"+
                "room_id,"+
                "check_in_date,"+
                "check_out_date,"+
                "total_price,"+
                "quest_count,"+
                "quest_identy"+
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1,reservation.getQuest_name());
            pr.setString(2,reservation.getQuest_mail());
            pr.setString(3,reservation.getQuest_phone());
            pr.setInt(4,reservation.getRoom_id());
            pr.setDate(5, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDate(6, Date.valueOf(reservation.getCheck_out_date()));
            pr.setDouble(7,reservation.getTotal_price());
            pr.setInt(8,reservation.getQuest_count());
            pr.setString(9,reservation.getQuest_identy());

            return pr.executeUpdate()!= -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
    public boolean updateReservation(Reservation reservation){

        String query="UPDATE public.reservation SET " +
                "quest_name=?," +
                "quest_mail=?," +
                "quest_phone=?," +
                "quest_identy=?" +
                " WHERE id=?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, reservation.getQuest_name());
            pr.setString(2, reservation.getQuest_mail());
            pr.setString(3, reservation.getQuest_phone());
            pr.setString(4, reservation.getQuest_identy());
            pr.setInt(5,reservation.getId());
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query="DELETE FROM public.reservation WHERE id=?";
        try{
            PreparedStatement pr=this.connection.prepareStatement(query);
            pr.setInt(1,id);

            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Reservation> getByListReservationId(int id){
        return this.selectByQuery("SELECT * FROM public.reservation WHERE id="+id);
    }
    public ArrayList<Reservation> selectByQuery(String query){//hazır bir SQL sorgu metodu oluşturduk.
        ArrayList<Reservation> resList=new ArrayList<>();
        try {
            ResultSet rs=this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                resList.add(this.match(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }
}


