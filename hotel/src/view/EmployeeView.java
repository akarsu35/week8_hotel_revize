package view;

import business.*;
import core.Helper;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeView extends Layout{

    private  User user;
    private JPanel container;
    private JScrollPane scrl_employee;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_bottom;
    private JTabbedPane tab_hotel;
    private JTable tbl_hotel;
    private JTable tbl_room;
    private JPanel tab_oda;
    private JTable tbl_res;
    private JPanel pnl_res;
    private JPanel pnl_hotel;
    private JButton btn_hotelAdd;
    private JPanel pnl_pansion;
    private JPanel pnl_season;
    private JTable tbl_season;
    private JTable tbl_pansion;
    private JTextField fld_hotel_name;
    private JTextField fld_city;
    private JTextField fld_start_date;
    private JTextField fld_finish_date;
    private JTextField fld_adult_count;
    private JTextField fld_child_count;
    private JButton btn_search_room;
    private JButton btn_reset;
    private JButton btn_add_room;
    private Object[] col_emp;
    private EmployeeManager employeeManager;
    private DefaultTableModel tmdl_hotel=new DefaultTableModel();//default bir tablo oluşturduk.
    private DefaultTableModel tmdl_room=new DefaultTableModel();
    private DefaultTableModel tmdl_res=new DefaultTableModel();
    private Object[] col_hotel;
    private Object[] col_room;
    private HotelManager hotelManager=new HotelManager();
    private RoomManager roomManager=new RoomManager();
    private PensionManager pensionManager=new PensionManager();
    private Object[] col_pension;
    private Object[] col_res;
    private DefaultTableModel tmdl_pension=new DefaultTableModel();
    private DefaultTableModel tmdl_season=new DefaultTableModel();

    private Object[] col_season;
    private SeasonManager seasonManager=new SeasonManager();
    private ReservationManager reservationManager=new ReservationManager();
    private JPopupMenu hotelPopUp;
    private JPopupMenu roomPopUp;
    private JPopupMenu resPopUpMenu;
    private UserManager userManager=new UserManager();
    private Room room;


    public EmployeeView(User user) {
        this.user=user;
        this.add(container);
        this.guiInitilaze(1400, 800);//Layout sınıfından extends ettik.
        this.col_emp = col_emp;
        this.col_hotel=col_hotel;
        this.col_room=col_room;
        this.col_res=col_res;
        this.lbl_welcome.setText("Hoşgeldiniz : "+this.user.getUsername());
        this.room=new Room();

        loadHotelTable();
        loadRoomTable(null);
        loadHotelPensionComponent();
        loadPensionTable();
        loadSeasonTable();
        loadRoomRezervationComponent();
        loadReservationTable(null);
        loadReservationComponent();

        //Employee ekranında otel ekle butonu.Burada otel eklenecek.
        btn_hotelAdd.addActionListener(e -> {
            AddHotelView addHotelView=new AddHotelView();
            addHotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });
        btn_add_room.addActionListener(e -> {

            AddRoomView addRoomView=new AddRoomView();

                addRoomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }

                });

        });
        btn_search_room.addActionListener(e -> {//:TODO search butonu eklenmeli
            if(fld_child_count.getText().isEmpty() || fld_adult_count.getText().isEmpty()){
                Helper.showMsg("Lütfen Yetişkin ve Çocuk sayılarını giriniz.");
                return;
            }
            int selectAdultNum = Integer.parseInt(fld_adult_count.getText());
            int selectChildNum = Integer.parseInt(fld_child_count.getText());

            if(selectAdultNum<0 || selectChildNum<0){
                Helper.showMsg("Lütfen Yetişkin sayısı giriniz.");
            }


            if(!dateFormatCheck(fld_start_date.getText()) || !dateFormatCheck(fld_finish_date.getText())){
                Helper.showMsg("Gecersiz Tarih formatı.");
                return;
            }
            ArrayList<Room> roomList=this.roomManager.searchForTable(
                    fld_hotel_name.getText(),
                    fld_city.getText(),
                    fld_start_date.getText(),
                    fld_finish_date.getText(),
                    fld_adult_count.getText(),
                    fld_child_count.getText()

            );
            // Eğer sorgu sonucu boş ise uygun mesajı göster
            if (roomList.isEmpty()) {
                Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı.");
                return; // Veri yoksa işlemi sonlandır
            }
            ArrayList<Object[]> searchResult=this.roomManager.getForTable(col_room.length,roomList);
            loadRoomTable(searchResult);


        });

        btn_reset.addActionListener(e -> {//:TODO reset butonu eklenmeli
            loadRoomTable(null);

        });
    }
    public EmployeeView() {



    }
    public boolean dateFormatCheck(String date){
        // Tarih formatının doğruluğunu kontrol etmek için gerekli kodları buraya ekleyin

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
             Date parsedDate = sdf.parse(date);
             return true; // Geçerli tarih formatı
         } catch (ParseException e) {
             return false; // Geçersiz tarih formatı
         }
    }


    public void loadHotelPensionComponent(){
        tableRowSelect(this.tbl_hotel);
        this.hotelPopUp=new JPopupMenu();
        hotelPopUp.add("Pansiyon Tipi Ekle").addActionListener(e -> {
            int selectId=this.getTableSelectedRow(tbl_hotel,0);
            int hotel_id = (int) tmdl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0);

            AddPensionView pensionView=new AddPensionView(selectId);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        hotelPopUp.add("Sezon Ekle").addActionListener(e->{
            int selectId=this.getTableSelectedRow(tbl_hotel,0);
            AddSeasonView seasonView=new AddSeasonView(selectId);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        this.tbl_hotel.setComponentPopupMenu(hotelPopUp);
    }
    public void loadRoomRezervationComponent() {
        tableRowSelect(this.tbl_room);

        this.roomPopUp = new JPopupMenu();
        roomPopUp.add("Rezervasyon Yap").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_room, 0);


            String errorMsg = validateFields();
            if (errorMsg.isEmpty()) {
                int adultNum = Integer.parseInt(this.fld_adult_count.getText());
                int childNum = Integer.parseInt(this.fld_child_count.getText());

                AddReservationView addReservationView = new AddReservationView(
                        this.roomManager.getById(selectId),
                        this.fld_start_date.getText(),
                        this.fld_finish_date.getText(),
                        adultNum, childNum,null
                );
                addReservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                        loadRoomTable(null);
                    }
                });
            } else {
                Helper.showMsg(errorMsg);
            }
        });
        this.tbl_room.setComponentPopupMenu(roomPopUp);

    }
    public void loadReservationComponent(){
        tableRowSelect(this.tbl_res);
        this.resPopUpMenu=new JPopupMenu();
        resPopUpMenu.add("Güncelle").addActionListener(e->{
            int selectResId=this.getTableSelectedRow(tbl_res,0);
            Reservation selectReservation=this.reservationManager.getById(selectResId);
            int selectRoomId=selectReservation.getRoom_id();
            Room selectRoom=this.roomManager.getById(selectRoomId);

            UpdateReservationView resUpdate=new UpdateReservationView(selectRoom,
                    selectReservation.getCheck_in_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    selectReservation.getCheck_out_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    selectReservation.getAdult_count(),
                    selectReservation.getChild_count(),
                    selectReservation);
            resUpdate.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);

                }
            });
        });
        resPopUpMenu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")){
                int selectResId=this.getTableSelectedRow(this.tbl_res,0);
                int selectRoomId=this.reservationManager.getById(selectResId).getRoom_id();
                Room selectedRoom = this.roomManager.getById(selectRoomId);
                selectedRoom.setStock(selectedRoom.getStock()+1);
                this.roomManager.updateStock(selectedRoom);
                if(this.reservationManager.delete(selectResId)){
                    Helper.showMsg("done");
                    System.out.println("stock : "+this.roomManager.getById(selectRoomId).getStock());
                    System.out.println("After stock update: " + selectedRoom.getStock());
                    loadRoomTable(null);
                    loadReservationTable(null);
                }else{
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_res.setComponentPopupMenu(resPopUpMenu);
    }

    private String validateFields() {
        StringBuilder errorMsg = new StringBuilder();

        if (this.fld_start_date.getText().isEmpty()) {
            errorMsg.append("Giriş Tarihi boş olamaz.\n");
        }

        if (this.fld_finish_date.getText().isEmpty()) {
            errorMsg.append("Çıkış Tarihi boş olamaz.\n");
        }

        if (this.fld_adult_count.getText().isEmpty() || !isValidNumber(this.fld_adult_count)) {
            errorMsg.append("Yetişkin Sayısı geçerli bir sayı olmalıdır.\n");
        }

        if (this.fld_child_count.getText().isEmpty() || !isValidNumber(this.fld_child_count)) {
            errorMsg.append("Çocuk Sayısı geçerli bir sayı olmalıdır.\n");
        }

        return errorMsg.toString();
    }


    // JTextField'ın içeriğinin geçerli sayısal bir değer içerip içermediğini kontrol etmek için yardımcı metot
    private boolean isValidNumber(JTextField textField) {
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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

    public void loadHotelTable(){//employee hotel tablosunu databaseden çekme ve verileri gösterme
        this.col_hotel=new Object[]{"ID","Otel Adı","Adres","Mail","Telefon","Yıldız","Araç Parkı","Wifi","Havuz","Concierge","SPA","Oda Servisi"};
        ArrayList<Object[]> hotelList=this.hotelManager.getForTable(col_hotel.length,this.hotelManager.findAll());
        createTable(this.tmdl_hotel,this.tbl_hotel,col_hotel,hotelList);
    }
    public void loadRoomTable(ArrayList<Object[]> roomList){//employee ,oda tablosunu databaseden çekme ve verileri gösterme
        this.col_room=new Object[] {"ID","Otel Adı","Oda Tipi","Stok","Yetişkin Fiyatı","Çocuk Fiyatı","Yatak Kapasitesi","m2","TV","Minibar","Konsol","Kasa","Projeksiyon"};
        if(roomList==null){
            roomList=this.roomManager.getForTable(col_room.length,this.roomManager.findAll());
        }
        createTable(this.tmdl_room,this.tbl_room,col_room,roomList);

    }

    public void loadPensionTable(){//employee ,pansiyon tablosunu databeseden çekme ve verileri gösterme
        this.col_pension= new Object[] {"ID","Otel Id","Pansiyon Tipi"};
        ArrayList<Object[]> pensionList=this.pensionManager.getForTable(col_pension.length,this.pensionManager.findAll());
        createTable(this.tmdl_pension,this.tbl_pansion,col_pension,pensionList);

    }
    public void loadSeasonTable(){//employee, season tablosunu databeseden çekme ve verileri gösterme
        this.col_season= new Object[] {"ID","Otel Id","Başlangıç Tarihi","Bitiş Tarihi"};
        ArrayList<Object[]> seasonList=this.seasonManager.getForTable(col_season.length,this.seasonManager.findAll());
        createTable(this.tmdl_season,this.tbl_season,col_season,seasonList);

    }
    public void loadReservationTable(Reservation reservation){//employee, reservation tablosunu databeseden çekme ve verileri gösterme
        this.col_res= new Object[] {"ID","Oda Id","Başlangıç Tarihi","Bitiş Tarihi","Toplam Tutar","Misafir Sayısı","Misafir Adı","Misafir Kimlik No","Mail","Telefon"};
        ArrayList<Object[]> resList=this.reservationManager.getForTable(col_res.length,this.reservationManager.findAll());
        createTable(this.tmdl_res,this.tbl_res,col_res,resList);

    }






}
