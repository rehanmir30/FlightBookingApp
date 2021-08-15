package com.example.flightbookingapp.Model;

public class SavedFlight {
    String d_date, a_date, id,d_location,a_location,flight_num;

    public SavedFlight(String d_date, String a_date, String id, String d_location, String a_location, String flight_num) {
        this.d_date = d_date;
        this.a_date = a_date;
        this.id = id;
        this.d_location = d_location;
        this.a_location = a_location;
        this.flight_num = flight_num;
    }

    public String getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(String flight_num) {
        this.flight_num = flight_num;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }

    public String getA_date() {
        return a_date;
    }

    public void setA_date(String a_date) {
        this.a_date = a_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getD_location() {
        return d_location;
    }

    public void setD_location(String d_location) {
        this.d_location = d_location;
    }

    public String getA_location() {
        return a_location;
    }

    public void setA_location(String a_location) {
        this.a_location = a_location;
    }
}
