package Model;

import Controller.Controller;
import Controller.User;
import java.sql.Connection;
import java.sql.SQLException;
public class Model  {

    private static Controller controller;
    private DataBase dataBase;
    private Connection db_connection;

    public Model() {
        dataBase = new DataBase("Vacation4u");
        dataBase.createNewDatabase();
        dataBase.connect();
        dataBase.createNewTable();
    }

    public void setController(Controller controller) {
        this.controller= controller;
    }

    public boolean createUser(User user) {
        try{
            dataBase.insert(user);
//            System.out.println("User should be on the DB:" + user);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public boolean editUser(String userNameToEdit, String optionToChange, String newValue) {
        try {
            return dataBase.updateUserData(userNameToEdit, optionToChange, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User searchUser(String userNameToSearch) {
        //return new User("DEMO123", "123456", "12.10.92", "FIRST DEMO", "LAST DEMO", "DEMO CITY");
        return dataBase.searchUser(userNameToSearch);
    }

    public boolean deleteUser(String userNameToDelete) throws SQLException {
        if(dataBase.deleteUser(userNameToDelete)==1){
              return true;
        }
        return false;
    }
}
