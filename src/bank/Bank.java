package bank;

import java.io.*;
import java.util.*;

public class Bank {
    private static final String FILE_PATH = "data/accounts.txt";
    private Map<String, Account> accounts = new HashMap<>();

    public Bank() {
        loadAccounts();
    }

    // Register a new user
    public void register(String accountNumber, String name, String password) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("❌ Account number already exists.");
            return;
        }

        Account newAccount = new Account(accountNumber, name, password, 0.0);
        accounts.put(accountNumber, newAccount);
        saveAccounts();
        System.out.println("✅ Registration successful.");
    }

    // Login with account number and password
    public Account login(String accountNumber, String password) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println("✅ Login successful.\n");
            return account;
        } else {
            System.out.println("❌ Invalid account number or password.");
            return null;
        }
    }

    // Deposit money
    public void deposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        saveAccounts();
        System.out.println("✅ Deposit successful.");
    }

    // Withdraw money
    public void withdraw(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
            return;
        }
        if (account.getBalance() < amount) {
            System.out.println("❌ Insufficient balance.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        saveAccounts();
        System.out.println("✅ Withdrawal successful.");
    }

    // Save all account data to file
    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account acc : accounts.values()) {
                writer.write(acc.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving accounts: " + e.getMessage());
        }
    }

    // Load accounts from file into memory
    private void loadAccounts() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String accNo = parts[0];
                    String name = parts[1];
                    String pass = parts[2];
                    double balance = Double.parseDouble(parts[3]);
                    Account acc = new Account(accNo, name, pass, balance);
                    accounts.put(accNo, acc);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading accounts: " + e.getMessage());
        }
    }
}
