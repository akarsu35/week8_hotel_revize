package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Db instance=null;
    private Connection connection=null;
    private final String DB_URL="jdbc:postgresql://localhost:5433/hotel";
    private final String DB_USERNAME="postgres";
    private final String DB_PASSWORD="0571";

    public Db(){//database connection
        try{
            this.connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public Connection getConnection() {//javanın Connection sınıfının getConnection metodu
        return connection;
    }//database bağlantısını sağlayan metod
    public static Connection getInstance()  {//hazır metod çünkü herzaman bağlantı için lazım.
        try {
            if(instance==null|| instance.connection.isClosed()){//Db sınıfından başlangıçta instance=null bir değişken oluşturduk.
                instance=new Db();                              //burada ise bu instance=null yada bu instance ın bağlantısı(connection) kapalı ise
            }                                                   //Db sınıfından yeni bir instance oluştur.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();                        //sonrasında Db sınıfından oluşturdğuğun değişken ile database bağlantısı kur.
    }
}

