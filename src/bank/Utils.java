package bank;

import java.util.Scanner;

public class Utils {

    // Read a non-empty line
    public static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Input cannot be empty. Try again: ");
            }
        } while (input.isEmpty());
        return input;
    }

    // Read a double safely
    public static double readDouble(Scanner scanner, String prompt) {
        double value = 0.0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("❌ Amount cannot be negative.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
        return value;
    }
}
