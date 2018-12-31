package Model;

public class UserTradingMessage extends UserMessage {
    private String vacationOffer;

    public UserTradingMessage(String vacationOffering, User fromUser, String vacationToGet, User toUser, String status) {
        super(vacationToGet, fromUser, toUser, status);
        this.vacationOffer = vacationOffering;
    }

    public String getVacationOffer() {
        return vacationOffer;
    }

    public void setVacationOffer(String vacationOffer) {
        this.vacationOffer = vacationOffer;
    }
}
