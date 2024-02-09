package entity;

import core.ComboItem;

public class Hotel {
    private int id;
    private String name;
    private String adress;
    private String mail;
    private String phone;
    private String  star;
    private boolean car_park;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean roomservice;

    public Hotel(String name, String adress, String mail, String phone, String star, boolean car_park, boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean roomservice) {
        this.name = name;
        this.adress = adress;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
        this.car_park = car_park;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.roomservice = roomservice;
    }
    public ComboItem getComboItem(){
        return new ComboItem(this.getId(),this.getName());
    }

    public String toString() {
        return "Hotel{" +
                "ID=" + id +
                ", hotel_id=" + id +
                ", name='" + name + '\'' +
                ", adress=" + adress +
                ", mail='" + mail + '\'' +
                ", phone=" + phone +
                ", star=" + star +
                ", car_park=" + car_park +
                ", wifi=" + wifi +
                ", pool=" + pool +
                ", fitness=" + fitness +
                ", concierge=" + concierge +
                ", spa=" + spa +
                ", room_service=" + roomservice +
                '}';
    }

    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String  getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isCar_park() {
        return car_park;
    }

    public void setCar_park(boolean car_park) {
        this.car_park = car_park;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoomservice() {
        return roomservice;
    }

    public void setRoomservice(boolean roomservice) {
        this.roomservice = roomservice;
    }
}
