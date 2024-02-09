package business;

import core.Helper;
import dao.HotelDao;
import dao.ReservationDao;
import entity.Hotel;
import entity.Reservation;
import entity.User;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao = new ReservationDao();
    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }
    public ArrayList<Reservation> findAll(){return this.reservationDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Reservation> reservations){
        ArrayList<Object[]> resList = new ArrayList<>();
        for(Reservation obj : reservations){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getQuest_count();
            rowObject[i++] = obj.getQuest_name();
            rowObject[i++] = obj.getQuest_identy();
            rowObject[i++] = obj.getQuest_mail();
            rowObject[i++] = obj.getQuest_phone();

            resList.add(rowObject);
        }
        return resList;
    }

    public boolean save(Reservation reservation){
        if(reservation.getId()!=0){
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }
    public boolean updateReservation(Reservation reservation){
        if(this.getById(reservation.getId())==null){
            Helper.showMsg("notFound");
            return false;
        }
        return this.reservationDao.updateReservation(reservation);
    }

    public boolean delete(int id){
        if (this.getById(id)==null){
            Helper.showMsg(" Rezervasyon bulunamadı");
            return false;
        }
        for (Reservation reservation:this.reservationDao.getByListReservationId(id)){
            this.reservationDao.delete(reservation.getId());
        }
        return this.reservationDao.delete(id);
    }
}
