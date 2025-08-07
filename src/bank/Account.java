package bank;

public class Account {
    private String accountNumber;
    private String name;
    private String password;
    private double balance;

    public Account(String accountNumber, String name, String password, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Save account data as a line in the file
    public String toFileString() {
        return accountNumber + "," + name + "," + password + "," + balance;
    }

    // Display account details to user
    public void showInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: ₹" + balance);
    }
}
