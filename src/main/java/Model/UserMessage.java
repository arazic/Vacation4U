package Model;

public class UserMessage {
    private User fromUser; // purchaser
    private User toUser; // seller
    private String status;
    private String vacationToGet;



    public UserMessage(String vacationToGet, User fromUser, User toUser, String status) {
        this.vacationToGet = vacationToGet;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.status = status;
    }
    // what the buyer offer , who is the buyer,  what the buyer want, from how he want to buy


    public String getVacationToGet() {
        return vacationToGet;
    }

    public void setVacationToGet(String vacationToGet) {
        this.vacationToGet = vacationToGet;
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
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVacationOffer(){
        return "none";
    }


}
