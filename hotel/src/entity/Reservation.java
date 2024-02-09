package entity;

import core.ComboItem;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int room_id;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private double total_price;
    private int quest_count;
    private String quest_name;
    private String quest_identy;
    private String quest_mail;
    private String quest_phone;
    private Hotel hotel;
    private Room room;
    private Pension pension;
    private Season season;
    private int adult_count;
    private int child_count;

    public Reservation(int id, int room_id, LocalDate check_in_date, LocalDate check_out_date, double total_price, int quest_count, String quest_name, String quest_identy, String quest_mail, String quest_phone) {
        this.id = id;
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.total_price = total_price;
        this.quest_count = quest_count;
        this.quest_name = quest_name;
        this.quest_identy = quest_identy;
        this.quest_mail = quest_mail;
        this.quest_phone = quest_phone;
        this.adult_count=adult_count;
        this.child_count=child_count;
    }

    public Reservation() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getQuest_count() {
        return quest_count;
    }

    public void setQuest_count(int quest_count) {
        this.quest_count = quest_count;
    }

    public String getQuest_name() {
        return quest_name;
    }

    public void setQuest_name(String quest_name) {
        this.quest_name = quest_name;
    }

    public String getQuest_identy() {
        return quest_identy;
    }

    public void setQuest_identy(String quest_identy) {
        this.quest_identy = quest_identy;
    }

    public String getQuest_mail() {
        return quest_mail;
    }

    public void setQuest_mail(String quest_mail) {
        this.quest_mail = quest_mail;
    }

    public String getQuest_phone() {
        return quest_phone;
    }

    public void setQuest_phone(String quest_phone) {
        this.quest_phone = quest_phone;
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }
}
