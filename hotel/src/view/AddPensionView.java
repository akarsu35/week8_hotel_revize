package view;

import business.EmployeeManager;
import business.HotelManager;
import business.PensionManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class AddPensionView extends Layout{
    private final EmployeeManager employeeManager;
    private JPanel container;
    private JLabel lbl_pension;
    private JLabel lbl_otel_id;
    private JComboBox cmb_pension;
    private JButton btn_save;
    private Pension pension;
    private Hotel hotel=new Hotel();
    private int hotel_id;
    private PensionManager pensionManager;

    public AddPensionView(int hotel_id) {
        this.employeeManager = new EmployeeManager();
        this.pensionManager = new PensionManager();
        this.pension=new Pension();
        this.hotel_id=hotel_id;
        //this.hotelManager = new HotelManager();
        //this.hotel = new Hotel();
        this.add(container);
        this.guiInitilaze(600, 400);//Layout sınıfından extends ettik.
        this.lbl_otel_id.setText("Hotel Id: "+this.hotel_id);

        btn_save.addActionListener(e -> {

            if(this.cmb_pension.getSelectedItem().toString().equals(null)){
                Helper.showMsg("fill");

            }else {
                Pension pension = new Pension();
                this.pension.setPension_type(this.cmb_pension.getSelectedItem().toString());
                this.pension.setHotel_id(this.hotel_id);
                if(this.pensionManager.save(this.pension)){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
