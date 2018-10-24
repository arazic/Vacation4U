package sample.Model;

import sample.Controller;
import sample.User;

public class Model  {

    private static Controller controller;
    private static DateBase dateBase;

    public Model() {
        dateBase.connect();
        dateBase.createNewDatabase("MySql.db");
    }

    public void setController(Controller controller) {
        this.controller= controller;
    }

    public void createUser(User user) {
        dateBase.createNewTable();
        System.out.println("User in model:" + user);

    }
}
