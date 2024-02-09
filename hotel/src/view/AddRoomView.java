package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AddRoomView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel;
    private JComboBox cmb_pension;
    private JComboBox cmb_season;
    private JComboBox cmb_room_type;
    private JTextField fld_stock;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed_capacity;
    private JTextField fld_square;
    private JRadioButton rbtn_tv;
    private JRadioButton rbtn_minibar;
    private JRadioButton rbtn_game;
    private JRadioButton rbtn_box;
    private JRadioButton rbtn_projection;
    private JButton btn_save;
    private Hotel hotel;
    private Room room;
    private Season season;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private ComboItem comboItem=new ComboItem();
    private EmployeeView employeeView =new EmployeeView();

    public AddRoomView() {
        this.hotel = new Hotel();

        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.room = new Room();
        this.season = season;
        this.add(container);
        this.guiInitilaze(600, 400);


        for (Hotel hotel : hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getComboItem());
        }
        this.cmb_hotel.addActionListener(e -> {
            ArrayList<Season> seasons =this.seasonManager.getSeasonsByOtelId(((ComboItem)this.cmb_hotel.getSelectedItem()).getKey());
            System.out.println("sezonlar: "+seasons);
            cmb_season.removeAllItems();
            for(Season season:seasons){
                cmb_season.addItem(season.getComboItem());
            }

            ComboItem selectedOtelItem=(ComboItem)cmb_hotel.getSelectedItem();
            int selectedOtelId=selectedOtelItem.getKey();
            ArrayList<Pension> pensions =this.pensionManager.getPansionByOtelId(((ComboItem)this.cmb_hotel.getSelectedItem()).getKey());

            System.out.println("pansiyon: "+pensions);
            cmb_pension.removeAllItems();

            for(Pension pension:pensions){
                cmb_pension.addItem(pension.getComboItem());
            }
        });

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_adult_price,this.fld_bed_capacity,this.fld_child_price,this.fld_stock,this.fld_square})){
                Helper.showMsg("fill");

            }else {
                boolean result=false;
                ComboItem selectedHotel=(ComboItem) this.cmb_hotel.getSelectedItem();
                ComboItem selectedPension=(ComboItem) this.cmb_pension.getSelectedItem();//: TODO hata veriyor
                ComboItem selectedSeason=(ComboItem) this.cmb_season.getSelectedItem();//: TODO hata veriyor
                this.room.setSeason_id(selectedSeason.getKey());
                this.room.setPension_id(selectedPension.getKey());
                this.room.setHotel_id(selectedHotel.getKey());
                this.room.setType((String) this.cmb_room_type.getSelectedItem());
                this.room.setStock(Integer.parseInt(this.fld_stock.getText()));
                this.room.setAdult_price(Double.parseDouble(this.fld_adult_price.getText()));
                this.room.setChild_price(Double.parseDouble(this.fld_child_price.getText()));
                this.room.setBed_capacity(Integer.parseInt(this.fld_bed_capacity.getText()));
                this.room.setSquer_meter(Integer.parseInt(this.fld_square.getText()));
                this.room.setTelevision(this.rbtn_tv.isSelected());
                this.room.setMinibar(this.rbtn_minibar.isSelected());
                this.room.setGame_console(this.rbtn_game.isSelected());
                this.room.setCash_box(this.rbtn_box.isSelected());
                this.room.setProjection(this.rbtn_projection.isSelected());

                if(this.room.getId()!=0){
                    result = this.roomManager.update(this.room);
                }else{
                    result=this.roomManager.save(this.room);
                }
                if(result){
                    Helper.showMsg("done");
                    this.employeeView.loadReservationTable(null);
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }
            //this.employeeView.loadReservationTable();

        });
    }
}