package view;

import business.EmployeeManager;
import business.SeasonManager;
import core.Helper;
import entity.Pension;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.LocalDate.parse;

public class AddSeasonView extends Layout{
    private JPanel container;
    private JLabel lbl_season;
    private JTextField fld_start_date;
    private JTextField fld_finish_date;
    private JButton btn_save;
    private JLabel lbl_hotel_id;
    private Season season;
    private String  start_date;
    private String finish_date;

    private int hotel_id;
    private SeasonManager seasonManager;

    public AddSeasonView(int hotel_id){

        this.seasonManager = new SeasonManager();
        this.hotel_id=hotel_id;
        this.finish_date=finish_date;
        this.season=new Season();
        this.start_date=start_date;
        this.add(container);
        this.guiInitilaze(400, 250);//Layout sınıfından extends ettik.
        this.lbl_hotel_id.setText("Hotel Id: "+this.hotel_id);
        btn_save.addActionListener(e -> {
            JTextField[] checkList={
                    this.fld_start_date,
                    this.fld_finish_date
            };
            if (Helper.isFieldListEmpty(checkList)){
                Helper.showMsg("fill");
            }else{
                Season season=new Season();
                season.setHotel_id(this.hotel_id);
                season.setStart_date(LocalDate.parse(fld_start_date.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                season.setFinish_date(LocalDate.parse(fld_finish_date.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                if(this.seasonManager.save(season)){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });
    }

}
