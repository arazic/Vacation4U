package Model;


import Controller.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String city;
    private boolean logIn;
    private int messageNum;
    private List<UserMessage> incomingReqMessages;
    private List<UserMessage> incomingAnsMessages;
    private List<UserTradingMessage> incomingTradingReqMessages;
    private List<UserTradingMessage> incomingTradingAnsMessages;
    private List<Vacation> myVacations;
    private String phoneNumber;


    public User(String userName, String password, String birthDate, String firstName, String lastName, String city, String phoneNumber, boolean logIn) {
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.logIn= false;
        this.messageNum=0;
        this.incomingReqMessages = new ArrayList<UserMessage>();
        this.incomingAnsMessages = new ArrayList<UserMessage>();
        this.incomingTradingReqMessages = new ArrayList<UserTradingMessage>();
        this.incomingTradingAnsMessages = new ArrayList<UserTradingMessage>();
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
        return incomingReqMessages.size()+ incomingAnsMessages.size()
                +incomingTradingAnsMessages.size()+ incomingTradingReqMessages.size();
    }

    public int getConfirmedMessageNum(){
        int msgToShowCounter = 0;
        for (int i = 0; i < incomingReqMessages.size(); i++) {
           if (!incomingReqMessages.get(i).getStatus().equals("confirm") && !incomingReqMessages.get(i).getStatus().equals("reject"))
               msgToShowCounter++;
        }
        for (int i = 0; i < incomingAnsMessages.size(); i++) {
            if (!incomingAnsMessages.get(i).getStatus().equals("waiting"))
                msgToShowCounter++;
        }
        for (int i = 0; i < incomingTradingAnsMessages.size(); i++) {
            if (!incomingTradingAnsMessages.get(i).getStatus().equals("waiting"))
                msgToShowCounter++;
        }
        for (int i = 0; i < incomingTradingReqMessages.size(); i++) {
            if (!incomingTradingReqMessages.get(i).getStatus().equals("confirm") && !incomingTradingReqMessages.get(i).getStatus().equals("reject"))
                msgToShowCounter++;
        }

        return msgToShowCounter;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public void addIncomingReqMessages(UserMessage message) {
        incomingReqMessages.add(message);
    }

    public void addIncomingTradingReqMessages(UserTradingMessage message) {
        incomingTradingReqMessages.add(message);
    }
    public void addIncomingTradingAnsMessages(UserTradingMessage message) {
        incomingTradingAnsMessages.add(message);
    }

    public void removeIncomingTradingReqMessages(UserMessage message) {
        incomingTradingReqMessages.remove(message);
    }

    public void removeIncomingTradingAnsMessages(UserMessage message) {
        incomingTradingAnsMessages.remove(message);
    }


    public void removeIncomingReqMessages(UserMessage message) {
        incomingReqMessages.remove(message);
    }


    public List<UserTradingMessage> getIncomingTradingReqMessages() {
        return incomingTradingReqMessages;
    }

    public void setIncomingTradingReqMessages(List<UserTradingMessage> incomingTradingReqMessages) {
        this.incomingTradingReqMessages = incomingTradingReqMessages;
    }

    public List<UserTradingMessage> getIncomingTradingAnsMessages() {
        return incomingTradingAnsMessages;
    }

    public void setIncomingTradingAnsMessages(List<UserTradingMessage> incomingTradingAnsMessages) {
        this.incomingTradingAnsMessages = incomingTradingAnsMessages;
    }
    public void addIncomingAnsMessages(UserMessage message) {
        incomingAnsMessages.add(message);
    }

    public void removeIncomingAnsMessages(UserMessage message) {
        incomingAnsMessages.remove(message);
    }

    public List<UserMessage> getIncomingReqMessages() {
        return incomingReqMessages;
    }

    public void setIncomingReqMessages(List<UserMessage> incomingReqMessages) {
        this.incomingReqMessages = incomingReqMessages;
    }

    public List<UserMessage> getIncomingAnsMessages() {
        return incomingAnsMessages;
    }

    public void setIncomingAnsMessages(List<UserMessage> incomingAnsMessages) {
        this.incomingAnsMessages = incomingAnsMessages;
    }


    public List<Vacation> getMyVacations() {
        return myVacations;
    }
    public void setMyVacations(List<Vacation> myVacations) {
        this.myVacations = myVacations;
    }
    public void buyVacation(Vacation vacationToBuy) {
        myVacations.add(vacationToBuy);
    }

    public boolean sendBuyingRequestMessage(String flightNum, String sellerUser) {
        if (this.userName.equals(sellerUser))
            return false;
        else
            try{
                if (Vacation4UManager.getDataBase().insertMessage(this, flightNum, sellerUser)){
                    User seller = Vacation4UManager.getDataBase().searchUser(sellerUser);
                    UserMessage buyingRequestMessage = new UserMessage(flightNum, this, seller, "waiting");
                    seller.addIncomingReqMessages(buyingRequestMessage);
                }
            return true;
            } catch (Exception e) {
                return false;
            }
    }
//        return fromUser.sendTradingRequestMessage(offerVacationNum, requestFlightNum, toUser);

    public boolean sendTradingRequestMessage(String offerVacationNum, String requestFlightNum, String toUser) {
        if (this.userName.equals(toUser)){
            return false;
        }
        else{
            User toUserObject = Vacation4UManager.getDataBase().searchUser(toUser);
            if (Vacation4UManager.getDataBase().insertTradingMessage(offerVacationNum,this.userName, requestFlightNum,toUser,"waiting")){
                UserTradingMessage tradingRequestMessage = new UserTradingMessage(offerVacationNum, this, requestFlightNum, toUserObject, "waiting");
                toUserObject.addIncomingTradingReqMessages(tradingRequestMessage);
                return true;
            }
            return false;
        }
    }

    public boolean addVacationToSell(String flightNum, String from, String to, String airlineCompany, LocalDate fromDate, LocalDate toDate, String ticketType, String baggageWeight, String kind, String lodging){
        Vacation vacation = new Vacation(flightNum, from, to, airlineCompany, fromDate, toDate, ticketType, baggageWeight, kind, lodging, userName);
        return Vacation4UManager.getDataBase().addVacation(vacation);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
