package bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Welcome to Simple Bank ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            String choice = Utils.readLine(scanner, "Select an option: ");

            switch (choice) {
                case "1":
                    String accNo = Utils.readLine(scanner, "Enter account number: ");
                    String name = Utils.readLine(scanner, "Enter name: ");
                    String pass = Utils.readLine(scanner, "Enter password: ");
                    bank.register(accNo, name, pass);
                    break;

                case "2":
                    String loginAcc = Utils.readLine(scanner, "Enter account number: ");
                    String loginPass = Utils.readLine(scanner, "Enter password: ");
                    Account loggedIn = bank.login(loginAcc, loginPass);

                    if (loggedIn != null) {
                        boolean loggedInMenu = true;
                        while (loggedInMenu) {
                            System.out.println("\n=== Account Menu ===");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. View Account Info");
                            System.out.println("4. Logout");
                            String op = Utils.readLine(scanner, "Select an option: ");

                            switch (op) {
                                case "1":
                                    double dep = Utils.readDouble(scanner, "Enter amount to deposit: ");
                                    bank.deposit(loggedIn, dep);
                                    break;
                                case "2":
                                    double wd = Utils.readDouble(scanner, "Enter amount to withdraw: ");
                                    bank.withdraw(loggedIn, wd);
                                    break;
                                case "3":
                                    loggedIn.showInfo();
                                    break;
                                case "4":
                                    loggedInMenu = false;
                                    System.out.println("🔓 Logged out.");
                                    break;
                                default:
                                    System.out.println("❌ Invalid choice.");
                            }
                        }
                    }
                    break;

                case "3":
                    exit = true;
                    System.out.println("👋 Thank you for using Simple Bank!");
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }

        scanner.close();
    }
}
