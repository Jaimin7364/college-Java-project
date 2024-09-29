package com.busbooking.models;

public class Ticket {
    private int ticketId;
    private String passengerName;

    public Ticket(int ticketId, String passengerName) {
        this.ticketId = ticketId;
        this.passengerName = passengerName;
    }

    // Getters and setters (if needed)
    public int getTicketId() {
        return ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }
}
