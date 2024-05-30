/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configgg;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class NewClass extends JFrame {
    private JDateChooser checkInDateChooser;
    private JDateChooser checkOutDateChooser;
    private JButton calculateButton;

    public NewClass() {
        setTitle("Hotel Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel checkInLabel = new JLabel("Check-in Date:");
        checkInDateChooser = new JDateChooser();
        JLabel checkOutLabel = new JLabel("Check-out Date:");
        checkOutDateChooser = new JDateChooser();
        calculateButton = new JButton("Calculate Total Cost");

        mainPanel.add(checkInLabel);
        mainPanel.add(checkInDateChooser);
        mainPanel.add(checkOutLabel);
        mainPanel.add(checkOutDateChooser);
        mainPanel.add(new JLabel()); // Empty label for spacing
        mainPanel.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalCost();
            }
        });

        add(mainPanel);
    }

    private void calculateTotalCost() {
        // Get selected dates from JDateChooser
        Date checkInDate = checkInDateChooser.getDate();
        Date checkOutDate = checkOutDateChooser.getDate();

        // Convert Date objects to LocalDateTime
        LocalDateTime checkInDateTime = LocalDateTime.ofInstant(checkInDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime checkOutDateTime = LocalDateTime.ofInstant(checkOutDate.toInstant(), ZoneId.systemDefault());

        // Calculate total cost based on selected dates
        double totalCost = calculateTotalCost(checkInDateTime, checkOutDateTime);

        // Display total cost or perform further actions
        JOptionPane.showMessageDialog(this, "Total Cost: $" + totalCost);
    }

    // Method to calculate total cost based on check-in and check-out dates
    private double calculateTotalCost(LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
        // Your calculation logic here
        // For demonstration, let's assume a fixed room rate per day
        double roomRatePerDay = 100;
        long days = java.time.Duration.between(checkInDateTime, checkOutDateTime).toDays();
        return days * roomRatePerDay;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NewClass().setVisible(true);
        });
    }
}
    

