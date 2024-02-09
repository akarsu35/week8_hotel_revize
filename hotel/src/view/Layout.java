package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Layout extends JFrame {
    public void guiInitilaze(int width,int height){//panel özellikleri için genel bir metod
        //panelin özellikleri
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Hotel Management");
        this.setSize(width,height);

        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        this.setVisible(true);
    }
    //tablo oluşturma metodu.
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows){
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);//ekranda karşımıza çıkacak tabloda sutunların yer değiştirme özelliği kapattık
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);//ekranda karşımıza çıkacak tabloda sutunların içinin değiştirilmesini engelledik.

        DefaultTableModel clearModel=(DefaultTableModel) table.getModel();//tablo her açıldığında temizlensin
        clearModel.setRowCount(0);
        if(rows==null){// row'lar boş olabilir eğer boşsa boş bir arraylist döndürsün
            rows=new ArrayList<>();
        }
        for(Object[] row:rows){
            model.addRow(row);
        }

    }

    public int getTableSelectedRow(JTable table,int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

}

