package View;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    String password;
    String birthDate;
    String firstName;
    String lastName;
    String city;
    boolean logIn;
    int messageNum;
    List<userMessage> incomingReqMessages;
    List<userMessage> incomingAnsMessages;
    List<userMessage> incomingTradingReqMessages;
    List<userMessage> incomingTradingAnsMessages;


    List<Vacation> myVacations;


    public User(String userName, String password, String birthDate, String firstName, String lastName, String city, boolean logIn) {
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.logIn= false;
        this.messageNum=0;
        this.incomingReqMessages = new ArrayList<userMessage>();
        this.incomingAnsMessages = new ArrayList<userMessage>();
        this.incomingTradingReqMessages = new ArrayList<userMessage>();
        this.incomingTradingAnsMessages = new ArrayList<userMessage>();
        this.myVacations = new ArrayList<Vacation>();
        //Vacation test = new Vacation("31d","israel","london","elal",)
    }

    @Override
    public String toString() {
        return "User:  " + "\n"+
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + "\n" +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' ;
    }

    public boolean isLegal() {
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getbirthDate() {
        return birthDate;
    }

    public void setbirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setLogIn(boolean logIn) {
        this.logIn = logIn;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public int getMessageNum() {
        return incomingReqMessages.size()+ incomingAnsMessages.size();
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public void addIncomingReqMessages(userMessage message) {
        incomingReqMessages.add(message);
    }

    public void addIncomingTradingReqMessages(userMessage message) {
        incomingTradingReqMessages.add(message);
    }
    public void addIncomingTradingAnsMessages(userMessage message) {
        incomingTradingAnsMessages.add(message);
    }

    public void removeIncomingTradingReqMessages(userMessage message) {
        incomingTradingReqMessages.remove(message);
    }

    public void removeIncomingTradingAnsMessages(userMessage message) {
        incomingTradingAnsMessages.remove(message);
    }


    public void removeIncomingReqMessages(userMessage message) {
        incomingReqMessages.remove(message);
    }


    public List<userMessage> getIncomingTradingReqMessages() {
        return incomingTradingReqMessages;
    }

    public void setIncomingTradingReqMessages(List<userMessage> incomingTradingReqMessages) {
        this.incomingTradingReqMessages = incomingTradingReqMessages;
    }

    public List<userMessage> getIncomingTradingAnsMessages() {
        return incomingTradingAnsMessages;
    }

    public void setIncomingTradingAnsMessages(List<userMessage> incomingTradingAnsMessages) {
        this.incomingTradingAnsMessages = incomingTradingAnsMessages;
    }
    public void addIncomingAnsMessages(userMessage message) {
        incomingAnsMessages.add(message);
    }

    public void removeIncomingAnsMessages(userMessage message) {
        incomingAnsMessages.remove(message);
    }

    public List<userMessage> getIncomingReqMessages() {
        return incomingReqMessages;
    }

    public void setIncomingReqMessages(List<userMessage> incomingReqMessages) {
        this.incomingReqMessages = incomingReqMessages;
    }

    public List<userMessage> getIncomingAnsMessages() {
        return incomingAnsMessages;
    }

    public void setIncomingAnsMessages(List<userMessage> incomingAnsMessages) {
        this.incomingAnsMessages = incomingAnsMessages;
    }



    public void buyVacation(Vacation vacationToBuy) {
        myVacations.add(vacationToBuy);
    }

}
