package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankFrame extends JFrame {

    public BankFrame() {
        setTitle("Simple Bank Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("Welcome to Simple Bank", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        // Add components to panel
        panel.add(title);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(exitButton);

        add(panel);

        // Actions
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterFrame();  // We'll create this next
        });

        loginButton.addActionListener(e -> {
            dispose();
            new LoginFrame();  // We'll create this next
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
