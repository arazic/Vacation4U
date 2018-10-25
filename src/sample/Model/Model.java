package sample.Model;

import sample.Controller;
import sample.User;

public class Model  {

    private static Controller controller;
    private static DateBase dateBase;

    public Model() {

        dateBase.connect();
        dateBase.createNewDatabase("MySql.db");
        dateBase.createNewTable();

    }

    public void setController(Controller controller) {
        this.controller= controller;
    }

    public boolean createUser(User user) {
        System.out.println("User in model:" + user);
        return true;
    }

    public boolean editUser(String userNameToedit, String optionToChange, String newValue) {
        return true;
    }

    public User searchUser(String userNameToSearch) {
        return new User("DEMO123", "123456", "12.10.92", "FIRST DEMO", "LAST DEMO", "DEMO CITY");
    }
}
