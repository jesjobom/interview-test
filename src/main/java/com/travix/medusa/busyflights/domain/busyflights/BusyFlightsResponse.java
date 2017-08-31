package com.travix.medusa.busyflights.domain.busyflights;

public class BusyFlightsResponse {
    
    private String airline;
    private String supplier;
    private Double fare;
    private String departure;
    private String destination;
    private String departureDate;
    private String arrivalDatel;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDatel() {
        return arrivalDatel;
    }

    public void setArrivalDatel(String arrivalDatel) {
        this.arrivalDatel = arrivalDatel;
    }
    
    
}
