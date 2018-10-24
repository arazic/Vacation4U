package sample;

import java.time.LocalDate;
import java.util.Date;

public class User {
    String userName;
    String password;
    LocalDate birthDate;
    String firstName;
    String lastName;
    String city;

    public User(String userName, String password, LocalDate birthDate, String firstName, String lastName, String city) {
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
}
