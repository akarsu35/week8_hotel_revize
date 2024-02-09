package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao=new UserDao();
    }
    public User findByLogin(String username, String password){//UserDao unun findByLogin() metodunu çağırıp kullanıyoruz.
        return this.userDao.findByLogin(username, password);
    }
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }
    public ArrayList<User> getByListUserId(int id){
        return this.userDao.getByListUserId(id);

    }
    public boolean save(User user){
        if(user.getId()!=0){
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }
    public User getById(int id){
       return this.userDao.getById(id);
    }
    public boolean delete(int id){
        if (this.getById(id)==null){
            Helper.showMsg(" Kullanıcı bulunamadı");
            return false;
        }
        for (User user:this.userDao.getByListUserId(id)){
            this.userDao.delete(user.getId());
        }
        return this.userDao.delete(id);
    }
    public boolean update(User user){

        if(this.getById(user.getId())==null){
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<User> userList){
        ArrayList<Object[]> userObjList=new ArrayList<>();
        for(User obj:userList){
            int i=0;
            Object[] rowObject=new Object[size];
            rowObject[i++]=obj.getId();
            rowObject[i++]=obj.getUsername();
            rowObject[i++]=obj.getPassword();
            rowObject[i++]=obj.getRole();
            userObjList.add(rowObject);

        }
        return userObjList;
    }
    public ArrayList<User> searchForTable(String role){
        String select="SELECT * FROM public.user";
        ArrayList<String> whereList=new ArrayList<>();
        if(role!=null){
            whereList.add("user_role='"+role+"'");
        }

        String whereStr=String.join(" AND ",whereList);
        String query=select;
        if(whereStr.length()>0){
            query+=" WHERE "+whereStr;
        }
        return this.userDao.selectByQuery(query);
    }



}
