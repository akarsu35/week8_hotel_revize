package view;

import business.UserManager;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;

public class UserNewView extends Layout{

    private JPanel container;
    private JLabel lbl_new;
    private JLabel lbl_username;
    private JLabel lbl_userpass;
    private JLabel lbl_role;
    private JTextField fld_username;
    private JTextField fld_userpass;
    private JButton btn_save;
    private JComboBox cmb_role;
    private User user;
    private UserDao userDao;
    private UserManager userManager=new UserManager();


    public UserNewView(User user) {
        this.userManager=new UserManager();
        this.user=user;

    }
    public UserNewView() {
        this.add(container);
        this.guiInitilaze(400,400);//Layout sınıfından extends ettik.

        btn_save.addActionListener(e -> {//admin kullanıcı ekranında sağ tık ile açılan popup menude "Yeni" için çıkan menünün kaydet buton işlemi
            JTextField[] checkFildList ={this.fld_username,this.fld_userpass};//iki tane Jtext olduğu için liste oluşturduk.


            if(Helper.isFieldListEmpty(checkFildList)){//yukarıdaki listenin herhangi birinin boş olup olmadığını kontrol ettik.
                Helper.showMsg("fill");
            }else{
                boolean result=true;
                if(this.user==null){

                    User obj=new User(this.fld_username.getText(),this.fld_userpass.getText(), (String) cmb_role.getSelectedItem());//FieldText lere girilen değerlere ait user
                    result=this.userManager.save(obj);

                }
                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }

        });
    }
    public User getById(int id){
        return this.userDao.getById(id);
    }
}
