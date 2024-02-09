package core;

import javax.swing.*;
import java.awt.*;

public class Helper {//yardımcı sınıflar.hazır kodları içerir.
    public static void setTheme(){//arayüz temasını Nimbus yapar.
        //Nimbus biraz daha farklı bir arayüz için kullanıldı.
        for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }

    }
    public static void showMsg(String  str){//pop up hata mesajı yada başka mesajlar için
        optionPaneTR();
        String msg;
        String title;
        switch (str){
            case "fill"->{
                msg="lütfen tüm alanları doldurunuz !";
                title="Hata";
            }
            case "done"->{
                msg="İşlem Başarılı !";
                title="Sonuç";
            }
            case "notFound" ->{
                msg="Kayıt bulunamadı !";
                title="Bulunamadı";
            }
            case "error" ->{
                msg="Hatalı işlem yaptınız !";
                title="Hata";
            }
            case "admin"->{
                msg="Admin girişi başarılı !";
                title="Sonuç";
            }
            case "employee"->{
                msg="Çalışan girişi başarılı !";
                title="Sonuç";
            }

            default ->{
                msg=str;
                title="Mesaj";
            }


        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }
    public static boolean confirm(String str){//silmek yada başka bir işlem yapmak istediğimizde bize"Emin misin?" soracak metod.
        String msg;
        optionPaneTR();
        if(str.equals("sure")){
            msg="Bu işlemi yapmak istediğine emin misin ?";
        }else {
            msg= str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION)==0;
    }

    public static void optionPaneTR(){//confirm() metodundaki ingilizce metinleri türkçe yapma metodu.
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }
    public static boolean isFieldEmpty(JTextField field){//field ların boş olup olmadığını kontrol eder
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){//field ları liste haline çevirdik.
        for(JTextField field:fieldList){//türü JTextField olan field nesnesini fieldList'teki elemanları teker teker gezer,
            if(isFieldEmpty(field)) return true;//eğer field'lar boşsa true
        }
        return false;//yanlışsa false çevirir.
    }
    public static int getLocationPoint(String type, Dimension size){//Jpanellerin ekranda nerede görüneceğini belirler.
        return switch (type) {
            case "x" ->
                    (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;//ekranın yatay boyutundan girilen "size" değerini çıkarıyoruz
            case "y" ->
                    (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;//ekranın dikey boyutundan girilen "size" değerini çıkarıyoruz
            default -> 0;
        };
    }


}
