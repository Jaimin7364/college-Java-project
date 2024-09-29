package com.busbooking.services;

import com.busbooking.models.Bus;
import com.busbooking.models.Ticket;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Bus> buses = new ArrayList<>();
    private int ticketCounter = 0;

    public Ticket bookTicket(int busId, String passengerName, int numberOfSeats) {
        for (Bus bus : buses) {
            if (bus.getId() == busId && bus.bookSeats(numberOfSeats)) {
                return new Ticket(++ticketCounter, passengerName);
            }
        }
        return null; // No available seats
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public List<Bus> getBuses() {
        return buses;
    }
}
