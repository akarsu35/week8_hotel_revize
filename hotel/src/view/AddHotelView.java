package view;

import business.EmployeeManager;
import business.HotelManager;
import core.Helper;
import entity.Employee;
import entity.Hotel;
import entity.User;

import javax.swing.*;

public class AddHotelView extends Layout{
    private JPanel container;
    private JPanel pnl_name;
    private JPanel pnl_features_l;
    private JPanel pnl_features_r;
    private JLabel lbl_welcome;
    private JTextField fld_h_name;
    private JTextField fld_h_mail;
    private JTextField fld_h_phone;
    private JTextField fld_h_address;
    private JTextField fld_h_star;
    private JRadioButton rbtn_car_park;
    private JRadioButton rbtn_wifi;
    private JRadioButton rbtn_pool;
    private JRadioButton rbtn_fitness;
    private JRadioButton rbtn_concierge;
    private JRadioButton rbtn_spa;
    private JRadioButton rbtn_room_service;
    private JButton btn_save;
    private EmployeeManager employeeManager;
    private HotelManager hotelManager;
    private Hotel hotel;

    public AddHotelView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(container);
        this.guiInitilaze(600,400);//Layout sınıfından extends ettik.

        btn_save.addActionListener(e -> {//employee kullanıcı ekranında otel ekle ile çıkan menüde kaydet buton işlemi
            JTextField[] checkFildList = {this.fld_h_name,this.fld_h_mail,this.fld_h_phone,this.fld_h_address,this.fld_h_address,this.fld_h_star};//4 tane Jtext olduğu için liste oluşturduk.


            if(Helper.isFieldListEmpty(checkFildList)){//yukarıdaki listenin herhangi birinin boş olup olmadığını kontrol ettik.
                Helper.showMsg("fill");
            }else{
                boolean result=true;
                this.hotel.setName(this.fld_h_name.getText());
                this.hotel.setMail(this.fld_h_mail.getText());
                this.hotel.setPhone(this.fld_h_phone.getText());
                this.hotel.setAdress(this.fld_h_address.getText());
                this.hotel.setStar(this.fld_h_star.getText());
                this.hotel.setCar_park(this.rbtn_car_park.isSelected());
                this.hotel.setWifi(this.rbtn_wifi.isSelected());
                this.hotel.setPool(this.rbtn_pool.isSelected());
                this.hotel.setFitness(this.rbtn_fitness.isSelected());
                this.hotel.setConcierge(this.rbtn_concierge.isSelected());
                this.hotel.setSpa(this.rbtn_spa.isSelected());
                this.hotel.setRoomservice(this.rbtn_room_service.isSelected());
                result = this.hotelManager.save(this.hotel);
                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }

        });
    }
}
