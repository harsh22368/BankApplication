package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register New Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // UI Components
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Username:");
        JTextField nameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel balanceLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField();

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(registerBtn);
        panel.add(backBtn);

        add(panel);

        // Register Button Logic
        registerBtn.addActionListener(e -> {
            String username = nameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String balanceStr = balanceField.getText().trim();

            if (username.isEmpty() || password.isEmpty() || balanceStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            double balance;
            try {
                balance = Double.parseDouble(balanceStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid balance.");
                return;
            }

            // Call the register method in Bank class
            boolean success = Bank.register(username, password, balance);

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                dispose();
                new BankFrame(); // back to home
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            }
        });

        // Back Button
        backBtn.addActionListener(e -> {
            dispose();
            new BankFrame();
        });

        setVisible(true);
    }
}
