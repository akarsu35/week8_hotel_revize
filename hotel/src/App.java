

import business.UserManager;
import entity.User;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        core.Helper.setTheme();
        //User user = new User();
        //UserDao userDao = new UserDao();
        LoginView loginView=new LoginView();//login paneli çağırmak
        //AdminView adminView=new AdminView(user);//admin paneli başlatmak
        UserManager userManager = new UserManager();
        //EmployeeView employeeView=new EmployeeView(userManager.findByLogin("bb","bb"));//employee paneli başlatmak


    }
}