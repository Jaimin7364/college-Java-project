package com.busbooking.ui;

import com.busbooking.models.Bus;
import com.busbooking.models.Ticket;
import com.busbooking.services.BookingService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookingSystemGUI extends JFrame {
    private BookingService bookingService;
    private JTextArea outputArea;
    private JTextField busIdField;
    private JTextField passengerNameField;
    private JTextField seatsField;
    private JTextField addBusIdField;
    private JTextField addBusNameField;
    private JTextField addBusSeatsField;
    private JTextField addBusRouteField;
    private JTextField addBusTimingField;

    public BookingSystemGUI() {
        bookingService = new BookingService();
        bookingService.addBus(new Bus(1, "City Express", 50, "Downtown - Uptown", "10:00 AM"));
        bookingService.addBus(new Bus(2, "Highway Star", 40, "Uptown - Suburbs", "11:00 AM"));

        setTitle("Bus Ticket Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a main panel with a border layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Input panel for booking
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Ticket"));

        inputPanel.add(new JLabel("Bus ID:"));
        busIdField = new JTextField();
        inputPanel.add(busIdField);

        inputPanel.add(new JLabel("Passenger Name:"));
        passengerNameField = new JTextField();
        inputPanel.add(passengerNameField);

        inputPanel.add(new JLabel("Number of Seats:"));
        seatsField = new JTextField();
        inputPanel.add(seatsField);

        JButton bookButton = new JButton("Book Ticket");
        bookButton.setBackground(Color.BLUE);
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(new BookTicketAction());
        inputPanel.add(bookButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Panel for adding a bus
        JPanel addBusPanel = new JPanel();
        addBusPanel.setLayout(new GridLayout(6, 2));
        addBusPanel.setBorder(BorderFactory.createTitledBorder("Add New Bus"));

        addBusPanel.add(new JLabel("New Bus ID:"));
        addBusIdField = new JTextField();
        addBusPanel.add(addBusIdField);

        addBusPanel.add(new JLabel("Bus Name:"));
        addBusNameField = new JTextField();
        addBusPanel.add(addBusNameField);

        addBusPanel.add(new JLabel("Total Seats:"));
        addBusSeatsField = new JTextField();
        addBusPanel.add(addBusSeatsField);

        addBusPanel.add(new JLabel("Route:"));
        addBusRouteField = new JTextField();
        addBusPanel.add(addBusRouteField);

        addBusPanel.add(new JLabel("Timing:"));
        addBusTimingField = new JTextField();
        addBusPanel.add(addBusTimingField);

        JButton addBusButton = new JButton("Add Bus");
        addBusButton.setBackground(Color.GREEN);
        addBusButton.setForeground(Color.WHITE);
        addBusButton.addActionListener(new AddBusAction());
        addBusPanel.add(addBusButton);

        // Adding components to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        mainPanel.add(addBusPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class BookTicketAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int busId = Integer.parseInt(busIdField.getText());
                String passengerName = passengerNameField.getText();
                int numberOfSeats = Integer.parseInt(seatsField.getText());
                Ticket ticket = bookingService.bookTicket(busId, passengerName, numberOfSeats);
                if (ticket != null) {
                    outputArea.append("Ticket booked for " + passengerName + " on bus " + busId + "\n");
                } else {
                    outputArea.append("No available seats for bus " + busId + "\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Please enter valid input.\n");
            }
        }
    }

    private class AddBusAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(addBusIdField.getText());
                String name = addBusNameField.getText();
                int totalSeats = Integer.parseInt(addBusSeatsField.getText());
                String route = addBusRouteField.getText();
                String timing = addBusTimingField.getText();

                Bus newBus = new Bus(id, name, totalSeats, route, timing);
                bookingService.addBus(newBus);
                outputArea.append("Bus added: " + name + " (ID: " + id + ")\n");

                // Clear the fields after adding
                addBusIdField.setText("");
                addBusNameField.setText("");
                addBusSeatsField.setText("");
                addBusRouteField.setText("");
                addBusTimingField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.append("Please enter valid bus details.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingSystemGUI gui = new BookingSystemGUI();
            gui.setVisible(true);
        });
    }
}
