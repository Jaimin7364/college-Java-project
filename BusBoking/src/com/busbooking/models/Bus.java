package com.busbooking.models;

public class Bus {
    private int id;
    private String name;
    private int totalSeats;
    private int availableSeats;
    private String route;
    private String timing;

    public Bus(int id, String name, int totalSeats, String route, String timing) {
        this.id = id;
        this.name = name;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats; // Initially, all seats are available
        this.route = route;
        this.timing = timing;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getRoute() {
        return route;
    }

    public String getTiming() {
        return timing;
    }

    public boolean bookSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }
}
