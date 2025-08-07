package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DashboardFrame extends JFrame {

    private String username;
    private JLabel balanceLabel;

    public DashboardFrame(String username) {
        this.username = username;
        setTitle("Welcome, " + username);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        balanceLabel = new JLabel("Balance: ₹" + getBalance());
        panel.add(balanceLabel);

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton logoutBtn = new JButton("Logout");

        depositBtn.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    updateBalance(amount);
                    JOptionPane.showMessageDialog(this, "Deposited ₹" + amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        withdrawBtn.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            if (amountStr != null && !amountStr.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (getBalance() >= amount) {
                        updateBalance(-amount);
                        JOptionPane.showMessageDialog(this, "Withdrawn ₹" + amount);
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new BankFrame();
        });

        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(logoutBtn);

        add(panel);
        setVisible(true);
    }

    private double getBalance() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    br.close();
                    return Double.parseDouble(parts[2]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private void updateBalance(double amountChange) {
        File inputFile = new File("accounts.txt");
        File tempFile = new File("accounts_temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    double newBalance = Double.parseDouble(parts[2]) + amountChange;
                    writer.write(parts[0] + "," + parts[1] + "," + newBalance);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Could not update account file.");
        }

        balanceLabel.setText("Balance: ₹" + getBalance());
    }


}
