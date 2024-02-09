package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao=new PensionDao();
    public ArrayList<Pension> getById(int id){
        return this.pensionDao.getById(id);
    }
    public ArrayList<Pension> findAll(){return this.pensionDao.findAll();}

    public boolean save(Pension pension){
        if(pension.getId()!=0){
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Pension> pensions){
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for(Pension obj : pensions){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }
    public ArrayList<Pension> getPansionByOtelId(int id){
        return this.pensionDao.getPansionByOtelId(id);
    }
}
