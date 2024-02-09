package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private final UserManager userManager;

    public LoginView() {
        this.userManager=new UserManager();
        this.add(container);
        this.guiInitilaze(400,400);//Layout sınıfından extends ettik.

        btn_login.addActionListener(e-> {
            JTextField[] checkFieldList={this.fld_username,this.fld_pass};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else {
                User loginUser=this.userManager.findByLogin(this.fld_username.getText(),this.fld_pass.getText());//JTextField'a girilen değerleri getText() metodu ile alıyor.


                if(loginUser==null){
                    core.Helper.showMsg("notFound");

                }else {

                    if(loginUser.getRole().equals("Admin")){//admin paneli acılacak
                        dispose();//login den sonra admin paneli açılınca login kapanacak
                        AdminView adminView=new AdminView(loginUser);//Admin paneli açılacak


                    }else if (loginUser.getRole().equals("Employee")){//employee paneli acılacak
                        dispose();//login den sonra employee paneli açılınca login kapanacak
                        EmployeeView employeeView=new EmployeeView(loginUser);//employee paneli açılacak.

                    }

                }
            }


        });
    }


}
