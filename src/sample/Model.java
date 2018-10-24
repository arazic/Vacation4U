package sample;

import java.time.LocalDate;
import java.util.Observable;

public class Model extends Observable {
    public void createUser(String userName, String password, LocalDate birthDate, String firstName, String lastName, String city) {
        User user = new User(userName, password, birthDate, firstName, lastName, city);
        System.out.println("Model Print: " + user.toString());
    }
}
