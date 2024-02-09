package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao = new HotelDao();
    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }
    public ArrayList<Hotel> findAll(){return this.hotelDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel> hotels){
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for(Hotel obj : hotels){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAdress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++]=obj.isRoomservice();
            hotelList.add(rowObject);
        }
        return hotelList;
    }

    public boolean save(Hotel hotel){
        if(hotel.getId()!=0){
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }
}
