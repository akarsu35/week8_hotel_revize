package entity;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;

import java.util.ArrayList;

public class Room {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private Season season;
    private Pension pension;
    private int pension_id;
    private int season_id;
    private String type;
    private int stock;
    private double adult_price;
    private double child_price;
    private int bed_capacity;
    private int squer_meter;
    private boolean television;
    private boolean minibar;
    private boolean game_console;
    private boolean cash_box;
    private boolean projection;

    public Room(int id,Pension pensions, Season seasons,int hotel_id, int pension_id, int season_id, String type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.pension_id = pension_id;
        this.season=season;
        this.pension=pension;
        this.type = type;


    }

    public Room() {

    }
    public String toString() {
        return "Room{" +
                "ID=" + id +
                ", Otel Adı=" + id +
                ", Oda Tipi='" + pension_id + '\'' +
                ", Stok=" + stock +
                ", Yetişkin Fiyatı='" + adult_price + '\'' +
                ", Çocuk Fiyatı=" + child_price +
                ", Yatak Kapasitesi=" + bed_capacity +
                ", m2=" + squer_meter +
                ", TV=" + television +
                ", Minibar=" + minibar +
                ", Konsol=" + game_console +
                ", Kasa=" + cash_box +
                ", Projeksiyon=" + projection +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public int setStock(int stock) {
        this.stock = stock;

        return stock;
    }

    public double getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(double adult_price) {
        this.adult_price = adult_price;
    }

    public double getChild_price() {
        return child_price;
    }

    public void setChild_price(double child_price) {
        this.child_price = child_price;
    }

    public int getBed_capacity() {
        return bed_capacity;
    }

    public void setBed_capacity(int bed_capacity) {
        this.bed_capacity = bed_capacity;
    }

    public int getSquer_meter() {
        return squer_meter;
    }

    public void setSquer_meter(int squer_meter) {
        this.squer_meter = squer_meter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }

    public boolean isCash_box() {
        return cash_box;
    }

    public void setCash_box(boolean cash_box) {
        this.cash_box = cash_box;
    }

    public boolean isProjection() {
        return projection;
    }


    public ArrayList<Season> getSeason() {
        SeasonManager seasonManager=new SeasonManager();
        return seasonManager.getById(season_id);
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public ArrayList<Pension> getPension() {
        PensionManager pensionManager=new PensionManager();
        return pensionManager.getById(pension_id);
    }



    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }
    public Hotel getHotel() {
        HotelManager hotelManager = new HotelManager();
        return hotelManager.getById(hotel_id);
    }


}
