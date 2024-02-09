package business;

import dao.EmployeeDao;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class EmployeeManager {
    private final EmployeeDao employeeDao;

    public EmployeeManager() {
        this.employeeDao= new EmployeeDao();
    }
    public ArrayList<User> findAll(){
        return this.employeeDao.findAll();
    }


}
