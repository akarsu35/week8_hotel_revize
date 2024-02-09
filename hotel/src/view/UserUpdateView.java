package view;

import business.UserManager;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;

public class UserUpdateView extends Layout{
    private JPanel container;
    private JLabel lbl_update;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_userpass;
    private JTextField fld_pass;
    private JComboBox cmb_role;
    private JLabel lbl_role;
    private JButton btn_update;
    private User user;
    private UserDao userDao;
    private UserManager userManager=new UserManager();

    public UserUpdateView(User user) {
        this.user = user;
        this.userManager = new UserManager();


        this.add(container);
        this.guiInitilaze(400, 400);//Layout sınıfından extends ettik.

        if (user != null) {
            this.fld_username.setText(user.getUsername());
            this.fld_pass.setText(user.getPassword());
        }
        btn_update.addActionListener(e -> { //kullanıcı girişi yaptıktan sonra("Kaydet")kontrol edip mesaj dönüyor.
            if (this.fld_username == null || this.fld_pass == null) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                if (this.user == null) {
                    User obj = new User(fld_username.getText(), fld_pass.getText(), cmb_role.getName());
                    result = this.userManager.save(obj);
                } else {
                    this.user.setUsername(fld_username.getText());
                    this.user.setPassword(fld_pass.getText());
                    this.user.setRole((String)cmb_role.getSelectedItem());//combobox içine yazılıp sonrasında seçilen role ataması.
                    result = this.userManager.update(this.user);
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
    }

}
