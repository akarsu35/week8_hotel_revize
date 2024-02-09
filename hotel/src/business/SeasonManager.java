package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private SeasonDao seasonDao=new SeasonDao();
    public ArrayList<Season> getById(int id){
        return this.seasonDao.getById(id);
    }
    public ArrayList<Season> findAll(){return this.seasonDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasons){
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for(Season obj : seasons){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getStart_date();
            rowObject[i++] = obj.getFinish_date();
            seasonList.add(rowObject);
        }
        return seasonList;
    }
    public boolean save(Season season){
        if(season.getId()!=0){
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }
    public ArrayList<Season> getSeasonsByOtelId(int id){
        return this.seasonDao.getSeasonsByOtelId(id);
    }


}
