package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JLabel lbl_order;
    private JComboBox cmb_users;
    private JButton btn_search;
    private JButton btn_cncl;
    private UserDao userDao=new UserDao();
    private DefaultTableModel tmdl_user=new DefaultTableModel();//default bir tablo oluşturduk.
    private User user;//adminview için user'a ihtiyaç var çünkü login olmuş kişi burada işlem yapacak.
    private UserManager userManager=new UserManager();
    private Object[] col_user;
    private JPopupMenu adminMenu;
    private UserUpdateView updateView;




    public AdminView(User user) {//adminview oluştururken herzaman bir user alacak
        this.user = user;
        this.add(container);
        this.guiInitilaze(800, 500);//Layout sınıfından extends ettik.
        this.col_user = col_user;

        loadUserComponent();
        loadUserTable(null);

    }
        //tmdl_user tablosuna verileri çektikten sonra bu verilere sağ tık ile fonksiyonlar(ekle,sil,güncelle) ekleme,bunun için private JPopupMenu adminMenu; ekliyoruz.
        //tableRowSelect(tbl_user);
    public void loadUserComponent(){
        tableRowSelect(this.tbl_user);
        this.adminMenu=new JPopupMenu();
        adminMenu.add("Yeni").addActionListener(e -> {
            UserNewView userNewView=new UserNewView();
            userNewView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        adminMenu.add("Güncelle").addActionListener(e->{
            int selectUserId=this.getTableSelectedRow(tbl_user,0);
            UserUpdateView userUpdate=new UserUpdateView(this.userManager.getById(selectUserId));
            userUpdate.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        adminMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectUserId=this.getTableSelectedRow(this.tbl_user,0);
                if(this.userManager.delete(selectUserId)){
                    Helper.showMsg("done");
                    loadUserTable(null);
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        //şimdi yukarıdaki Jpopupmenu yü tablomuza entegre ediyoruz.
        this.tbl_user.setComponentPopupMenu(adminMenu);

        //sağtık belirginleşmesi yani seçili satırın renginin değişmesi için
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row=tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row,selected_row);
            }
        });
        btn_search.addActionListener(e -> {
            String selectedUser= (String) this.cmb_users.getSelectedItem();

            ArrayList<User> userListBySearch=this.userManager.searchForTable(selectedUser);
            ArrayList<Object[]> userRowListBySearch=this.userManager.getForTable(col_user.length,userListBySearch);
            loadUserTable(userRowListBySearch);

        });



        this.btn_cncl.addActionListener(e -> {
            loadUserTable(null);
        });
    }
    public int getTableSelectedRow(JTable table,int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }
    public void loadUserTable(ArrayList<Object[]> userList) {//user tablosunun yenilenmesi
        //gui den j table oluşturduk
        //kendimiz defaulttable oluşturduk
        this.col_user = new Object[] {"ID", "Kullanıcı Adı", "Kullanıcı Şifresi", "Kullanıcı Rol"};
        if(userList==null){
            userList=this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user,this.tbl_user,col_user,userList);
    }
    public void loadFilterUserRole(){
        this.cmb_users.removeAllItems();
        for (User obj : userManager.findAll()) {
            this.cmb_users.addItem(new ComboItem(obj.getId(), obj.getRole()));
        }
        this.cmb_users.setSelectedItem(null);
    }


}
