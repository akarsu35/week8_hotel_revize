package view;

import business.ReservationManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Pension;
import entity.Reservation;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AddReservationView extends Layout{
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JTextField fld_star;
    private JRadioButton rbtn_carpark;
    private JRadioButton rbtn_wifi;
    private JRadioButton rbtn_pooll;
    private JRadioButton rbtn_fitness;
    private JRadioButton rbtn_concierge;
    private JRadioButton rbtn_spa;
    private JRadioButton rbtn_room_service;
    private JTextField fld_room_type;
    private JTextField fld_pension_type;
    private JTextField fld_start_date;
    private JTextField fld_finish_date;
    private JTextField fld_price;
    private JTextField fld_bed_capacity;
    private JTextField fld_square;
    private JRadioButton rbtn_tv;
    private JRadioButton rbtn_minibar;
    private JRadioButton rbtn_game;
    private JRadioButton rbtn_box;
    private JRadioButton projeksiyonRadioButton;
    private JTextField fld_quest_count;
    private JTextField fld_quest_name;
    private JTextField fld_quest_id;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JButton btn_save;
    private JRadioButton rbtn_projection;
    private String strt_date;
    //private int room_id;
    private String  fnsh_date;
    private int adlt_count;
    private int chld_count;
    private int room_id;
    private Room room;
    private ReservationManager reservationManager=new ReservationManager();
    private Season season;
    private Pension pension;
    private SeasonManager seasonManager;
    private Reservation reservation;
    private RoomManager roomManager;

    public AddReservationView(Room room, String start_date, String finish_date, int adult_num, int child_num,Reservation reservation) {//filtrelemeden gelen giriş çıkış terihi ,ytşkn sayısı çck sayısı
        this.add(container);
        this.room=room;
        this.strt_date=start_date;
        this.fnsh_date=finish_date;
        this.adlt_count=adult_num;
        this.chld_count=child_num;
        if (reservation==null){
            this.reservation=new Reservation();
            this.roomManager=new RoomManager();
        }

        int quest_count=adult_num+child_num;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(start_date, formatter);
        LocalDate endDate = LocalDate.parse(finish_date, formatter);
        long day_count = ChronoUnit.DAYS.between(startDate, endDate);

        double total_price= (int) ((int) ((this.room.getAdult_price()*adult_num+this.room.getChild_price()*child_num))*day_count);
        //this.roomManager=new RoomManager();

        this.guiInitilaze(800, 600);//Layout sınıfından extends ettik.
        this.fld_hotel_name.setText(this.room.getHotel().getName());
        this.fld_city.setText(this.room.getHotel().getAdress());
        this.fld_star.setText(this.room.getHotel().getStar());
        this.fld_room_type.setText(this.room.getType());
        this.fld_pension_type.setText(String.valueOf(this.room.getPension()));
        this.fld_start_date.setText(String.valueOf(this.strt_date));
        this.fld_finish_date.setText(String.valueOf(this.fnsh_date));
        this.fld_quest_count.setText(String.valueOf(quest_count));
        this.fld_bed_capacity.setText(String.valueOf(room.getBed_capacity()));
        this.fld_square.setText(String.valueOf(this.room.getSquer_meter()));
        this.fld_price.setText(String.valueOf(total_price));
        this.rbtn_tv.setSelected(this.room.isTelevision());
        this.rbtn_wifi.setSelected(this.room.getHotel().isWifi());
        this.rbtn_carpark.setSelected(this.room.getHotel().isCar_park());
        this.rbtn_pooll.setSelected(this.room.getHotel().isPool());
        this.rbtn_fitness.setSelected(this.room.getHotel().isFitness());
        this.rbtn_concierge.setSelected(this.room.getHotel().isConcierge());
        this.rbtn_spa.setSelected(this.room.getHotel().isSpa());
        this.rbtn_room_service.setSelected(this.room.getHotel().isRoomservice());
        this.rbtn_tv.setSelected(this.room.isTelevision());
        this.rbtn_minibar.setSelected(this.room.isMinibar());
        this.rbtn_game.setSelected(this.room.isGame_console());
        this.rbtn_box.setSelected(this.room.isCash_box());
        this.rbtn_projection.setSelected(this.room.isProjection());


        btn_save.addActionListener(e -> {

            JTextField[] checkFildList = {this.fld_quest_name,this.fld_quest_id,this.fld_mail,this.fld_phone};
            if (Helper.isFieldListEmpty(checkFildList)) {
                Helper.showMsg("fill");
            }else{
                boolean result;

                this.reservation.setTotal_price(Double.parseDouble((this.fld_price.getText())));
                this.reservation.setQuest_count(Integer.parseInt(this.fld_quest_count.getText()));
                this.reservation.setTotal_price(Double.parseDouble(this.fld_price.getText()));
                this.reservation.setQuest_name(this.fld_quest_name.getText());
                this.reservation.setQuest_identy(this.fld_quest_id.getText());
                this.reservation.setQuest_mail(this.fld_mail.getText());
                this.reservation.setQuest_phone(this.fld_phone.getText());
                this.reservation.setRoom_id(this.room.getId());
                this.reservation.setCheck_in_date(LocalDate.parse(this.strt_date,formatter));
                this.reservation.setCheck_out_date(LocalDate.parse(this.fnsh_date,formatter));

                result = this.reservationManager.save(this.reservation);
                if (result) {
                    Helper.showMsg("done");

                    this.roomManager.getById(this.room.setStock(this.room.getStock()-1));
                    this.roomManager.updateStock(this.room);//stock değiştirme
                    //System.out.println("stock : "+this.room.getStock());
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
