package sample;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class User {
    String userName;
    String password;
    String birthDate;
    String firstName;
    String lastName;
    String city;

    public User(String userName, String password, String birthDate, String firstName, String lastName, String city) {
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public boolean isLegal() {
        return true;
    }
}
