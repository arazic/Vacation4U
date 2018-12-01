package View;

import javafx.scene.control.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacation {
    String FlightNum;
    String FromPlace;
    String ToPlace;
    String Airline;
    Date FromDate;
    Date ToDate;
    int TicketNum;
    String baggage;
    int baggageWeight;
    String Back;
    Date BackDate;
    String Kind;
    String hotel;
    String salerName;

    public Vacation(String FlightNum, String FromPlace, String ToPlace, String Airline, Date FromDate,
                    Date ToDate, int TicketNum, String baggage, int baggageWeight,
                    String Back, Date BackDate, String Kind, String hotel, String salerName) {
        this.FlightNum = FlightNum;
        this.FromPlace = FromPlace;
        this.ToPlace = ToPlace;
        this.Airline = Airline;
        this.FromDate = FromDate;
        this.ToDate = ToDate;
        this.TicketNum = TicketNum;
        this.baggage = baggage;
        this.baggageWeight = baggageWeight;
        this.Back = Back;
        this.BackDate = BackDate;
        this.Kind = Kind;
        this.hotel = hotel;
        this.salerName = salerName;
    }


    public String getFlightNum() {
        return FlightNum;
    }

    public String getFromPlace() {
        return FromPlace;
    }

    public String getToPlace() {
        return ToPlace;
    }

    public String getAirline() {
        return Airline;
    }

    public Date getFromDate() {
        return FromDate;
    }

    public Date getToDate() {
        return ToDate;
    }

    public int getTicketNum() {
        return TicketNum;
    }

    public String getBaggage() {
        return baggage;
    }

    public int getBaggageWeight() {
        return baggageWeight;
    }

    public String getBack() {
        return Back;
    }

    public Date getBackDate() {
        return BackDate;
    }

    public String getKind() {
        return Kind;
    }

    public String getHotel() {
        return hotel;
    }

    public String getSalerName() {
        return salerName;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "FlightNum='" + FlightNum + '\'' +
                ", FromPlace='" + FromPlace + '\'' +
                ", ToPlace='" + ToPlace + '\'' +
                ", Airline='" + Airline + '\'' +
                ", FromDate=" + FromDate +
                ", ToDate=" + ToDate +
                ", TicketNum=" + TicketNum +
                ", baggage='" + baggage + '\'' +
                ", baggageWeight=" + baggageWeight +
                ", Back='" + Back + '\'' +
                ", BackDate=" + BackDate +
                ", Kind='" + Kind + '\'' +
                ", hotel='" + hotel + '\'' +
                ", salerName='" + salerName + '\'' +
                '}';
    }

    public String shortToString() {

        return
                "FlightNum='" + FlightNum + '\'' +
                        ", FromPlace='" + FromPlace + '\'' +
                        ", ToPlace='" + ToPlace + '\'' +
                        ", FromDate=" + FromDate +
                        ", ToDate=" + ToDate +
                        ", TicketNum=" + TicketNum;
    }

}


