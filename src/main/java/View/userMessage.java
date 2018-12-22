package View;

public class userMessage {

    String vacationToBuy;
    String vacationOffer;
    User fromUser; // purchaser
    User toUser; // saler
    String Status;


    public userMessage(String vacationToBuy, User fromUser, User toUser, String Status) {
        this.vacationToBuy = vacationToBuy;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.Status= Status;

    }
//    userMessage Message = new userMessage(vacationToBuy.getFlightNum(), registeredUser, vacationToOffer.getFlightNum(),  salerUser, "waiting");

    public userMessage(String vacationToBuy, User fromUser, String vacationOffering, User toUser, String status) {
        this.vacationToBuy = vacationToBuy;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.Status= status;
        this.vacationOffer = vacationOffering;
    }

    public String getVacationToBuy() {
        return vacationToBuy;
    }

    public void setVacationToBuy(String vacationToBuy) {
        this.vacationToBuy = vacationToBuy;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
