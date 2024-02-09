package dao;

import core.Db;
import core.Helper;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }
    public ArrayList<User> findAll(){//User listesini veritabanından alan SQL sorgu metodu
        ArrayList<User> userList=new ArrayList<>();
        String sql="SELECT * FROM public.user";
        try {
            ResultSet rs=this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));

            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        return userList;
    }
    public User findByLogin(String username, String password ){//JTextField'a girilen bilgiler doğrultusunda veri tabanı ile bağlantıya geçip eşleme(match) yapar.
        User obj=null; // Bu satır, User tipinde bir değişken tanımlar ve başlangıçta null olarak atar. Bu değişken, veritabanından alınan kullanıcı bilgilerini tutmak için kullanılacak.
        String query="SELECT * FROM public.user WHERE user_name=? AND user_pass=?"; // Bu satır, veritabanında çalıştırılacak olan SQL sorgusunu bir String değişkene atar. Sorgu, public.user tablosundan kullanıcı adı ve şifresi parametrelerle eşleşen tüm satırları seçer. ? işaretleri, parametrelerin yerine geçecek olan değerleri temsil eder.
        try { // Bu satır, bir try-catch bloğunun başlangıcını belirtir. Bu blok, veritabanı işlemleri sırasında oluşabilecek hataları yakalamak için kullanılır.
            PreparedStatement pr=this.con.prepareStatement(query); // Bu satır, this.con değişkeni ile veritabanına bağlantı kurar ve sorguyu hazırlar. this.con, Connection tipinde bir değişkendir ve veritabanı bağlantısını temsil eder. pr, PreparedStatement tipinde bir değişkendir ve hazırlanmış sorguyu temsil eder.
            pr.setString(1,username); // Bu satır, sorgudaki ilk ? işaretinin yerine username parametresinin değerini koyar.
            pr.setString(2,password); // Bu satır, sorgudaki ikinci ? işaretinin yerine password parametresinin değerini koyar.

            ResultSet rs=pr.executeQuery(); // Bu satır, hazırlanmış sorguyu veritabanında çalıştırır ve sonucu rs değişkenine atar. rs, ResultSet tipinde bir değişkendir ve sorgu sonucunu temsil eder.
            if(rs.next()){ // Bu satır, bir if koşulunun başlangıcını belirtir. Bu koşul, sorgu sonucunda bir satır olup olmadığını kontrol eder. rs.next() metodu, sonuç kümesinde bir sonraki satıra ilerler ve eğer varsa true döndürür.
                obj=this.match(rs);

            }

        } catch (SQLException e) { // Bu satır, try bloğunda SQLException tipinde bir hata oluşursa çalışacak olan catch bloğunun başlangıcını belirtir. e, hatayı temsil eden bir nesnedir.
            e.printStackTrace(); // Bu satır, hatanın izini standart hata akımına yazdırır.
        }
        return obj; // Bu satır, obj değişkeninin değerini döndürür. Eğer veritabanında eşleşen bir kullanıcı bulunamazsa, obj null olarak döner.
    }
    public User match(ResultSet rs) throws SQLException {//veri tabanındaki tabloda bulunan sütunların verilerini atama yapar.(set leme yapar yani)
        User obj=new User();
        obj.setId(rs.getInt("id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(rs.getString("user_role"));
        return obj;

    }
    public boolean save(User user){
        String sql="INSERT INTO public.user (user_name,user_pass,user_role) VALUES(?,?,?)";
        try{
            PreparedStatement pr=this.con.prepareStatement(sql);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
         return true;
    }
    public User getById(int id){
        User obj=null;
        String query="SELECT * FROM public.user WHERE id=?";
        try{
            PreparedStatement pr=this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                obj=this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;

    }
    public ArrayList<User> getByListUserId(int id){
        return this.selectByQuery("SELECT * FROM public.user WHERE id="+id);

    }public ArrayList<User> selectByQuery(String query){//hazır bir SQL sorgu metodu oluşturduk.
        ArrayList<User> userList=new ArrayList<>();
        try {
            ResultSet rs=this.con.createStatement().executeQuery(query);
            while (rs.next()){
                userList.add(this.match(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public boolean delete(int id){
        String query="DELETE FROM public.user WHERE id=?";
        try{
            PreparedStatement pr=this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(User user){
        String query="UPDATE public.user SET " +
                "user_name=?," +
                " user_pass=?," +
                " user_role=?" +
                " WHERE id=?";//BrandView içinde açılacak "Yeni" popup'a tıklayınca
        try{                                                            //pencerede(Marka Ekle/Düzenle) marka eklemek için SQL sorgusu.
            PreparedStatement pr=this.con.prepareStatement(query);
            pr.setString(1,user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getRole());
            pr.setInt(4,user.getId());

            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


}
