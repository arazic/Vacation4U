package Model;

import javafx.scene.control.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Vacation {
    private String flightNum;
    private String fromPlace;
    private String toPlace;
    private String airlineCompany;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String ticketType;
    private String baggage;
    private String tripType;
    private String lodging = "NOT INCLUDED";
    private String saler;
    private String BuyerName;

    public Vacation(String flightNum, String fromPlace, String toPlace, String airlineCompany, LocalDate fromDate, LocalDate toDate, String ticketType, String baggage, String tripType, String lodging, String saler, String BuyerName) {
        this.flightNum = flightNum;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.airlineCompany = airlineCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.ticketType = ticketType;
        this.baggage = baggage;
        this.tripType = tripType;
        if (lodging != null && !lodging.equals(""))
            this.lodging = lodging;
        this.saler = saler;
        this.BuyerName= BuyerName;
    }
    public Vacation(String flightNum, String fromPlace, String toPlace, String airlineCompany, LocalDate fromDate, LocalDate toDate, String ticketType, String baggage, String tripType, String lodging, String saler) {
        this.flightNum = flightNum;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.airlineCompany = airlineCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.ticketType = ticketType;
        this.baggage = baggage;
        this.tripType = tripType;
        if (lodging != null && !lodging.equals(""))
            this.lodging = lodging;
        this.saler = saler;
        this.BuyerName= "";
    }

    public String getSaler() {
        return saler;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getBaggage() {
        return baggage;
    }

    public String getTripType() {
        return tripType;
    }

    public String getLodging() {
        return lodging;
    }


    public String getBuyer() {
        return BuyerName;
    }

    public void setBuyer(String buyer) {
        this.BuyerName = buyer;
    }

    @Override
    public String toString() {

        return "Vacation" + '\n' +
                "flightNum: " + flightNum + '\n' +
                "fromPlace: " + fromPlace + '\n' +
                "toPlace: " + toPlace + '\n' +
                "airlineCompany: " + airlineCompany + '\n' +
                "fromDate: " + fromDate + '\n' +
                "toDate: " + toDate + '\n' +
                "ticketType: " + ticketType + '\n' +
                "baggage: " + baggage + '\n' +
                "tripType: " + tripType + '\n' +
                "lodging: " + lodging + '\n';
    }

    public String shortToString() {

        return
                "FlightNum='" + flightNum + '\'' +
                        ", FromPlace='" + fromPlace + '\'' +
                        ", ToPlace='" + toPlace + '\'' +
                        ", FromDate=" + fromDate +
                        ", ToDate=" + toDate +
                        ", TicketType=" + ticketType;
    }

}


