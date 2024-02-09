package business;

import core.Helper;
import dao.HotelDao;
import dao.RoomDao;
import entity.Hotel;
import entity.Room;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private RoomDao roomDao=new RoomDao();
    private Hotel hotel=new Hotel();

    public Room getById(int id){
        return this.roomDao.getById(id);
    }
    public ArrayList<Room> findAll(){return this.roomDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> rooms){
        ArrayList<Object[]> roomList = new ArrayList<>();
        for(Room obj : rooms){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getSquer_meter();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++]=obj.isGame_console();
            rowObject[i++]=obj.isCash_box();
            rowObject[i++]=obj.isProjection();
            roomList.add(rowObject);
        }
        return roomList;
    }
    public boolean save(Room room){
        if(this.getById(room.getId())!=null){
            Helper.showMsg("Böyle Bir Oda Bulunmakta");
            return false;
        }
        return this.roomDao.save(room);
    }
    public boolean updateStock(Room room){
        if(this.getById(room.getId())==null){

            return false;
        }
        return this.roomDao.updateStock(room);
    }
    public boolean update(Room room){
        if(this.getById(room.getId())==null){
            Helper.showMsg(room.getType()+" ID kayıtlı oda Bulunamadı");
            return false;
        }
        return this.roomDao.save(room);
    }
    //Kriterlere uygun oda arama metodu
    public ArrayList<Room> searchForTable(String hotel_name,
                                          String hotel_city,
                                          String startdate,
                                          String finishdate,
                                          String adultcount,
                                          String childcount ){
        String query="SELECT * FROM public.room r "+
                "LEFT JOIN public.hotel h ON r.hotel_id = h.id "+
                "LEFT JOIN public.hotel_season s ON r.season_id = s.id WHERE";
        ArrayList<String> whereList=new ArrayList<>();
        whereList.add(" r.stock > "+ 0);
        startdate= LocalDate.parse(startdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();
        finishdate= LocalDate.parse(finishdate, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString();


        whereList.add(" AND s.start_date <='"+ startdate +"'");
        whereList.add(" AND s.finish_date >='"+ finishdate +"'");

        if(hotel_name!=null){
            whereList.add(" AND h.name ILIKE '%"+hotel_name+"%'");
        }
        if(hotel_city!=null){
            whereList.add(" AND h.address ILIKE '%"+hotel_city+"%'");

        }
        if(adultcount!=null && !adultcount.isEmpty() && childcount!=null && !childcount.isEmpty()){
            try{
                int adultNumber = Integer.parseInt(adultcount);
                int childNumber = Integer.parseInt(childcount);
                int totalNumber = adultNumber+childNumber;
                whereList.add(" AND r.bed_capacity >= '"+ (totalNumber)+ "'");
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            query+=String.join("",whereList);
        }
        ArrayList<Room> resultQuery=this.roomDao.selectByQuery(query);
        return resultQuery;


    }


}
