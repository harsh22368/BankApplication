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
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter account number: ");
                    String accNo = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String pass = scanner.nextLine();
                    bank.register(accNo, name, pass);
                    break;

                case "2":
                    System.out.print("Enter account number: ");
                    String loginAcc = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    Account loggedIn = bank.login(loginAcc, loginPass);

                    if (loggedIn != null) {
                        boolean loggedInMenu = true;
                        while (loggedInMenu) {
                            System.out.println("\n=== Account Menu ===");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. View Account Info");
                            System.out.println("4. Logout");
                            System.out.print("Select an option: ");
                            String op = scanner.nextLine();

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
