package com.mycompany.main;

import java.util.Scanner;

public class Main {

    // Scanner for user input
    static Scanner input = new Scanner(System.in);

    // User details
    static String username;
    static String password;
    static String cellphone;

    // Registered details
    static String registeredUsername;
    static String registeredPassword;
    static String registeredCellphone;

    // Check username
    public static boolean checkUserName(String username) {
        // Username must be exactly 5 characters and contain an underscore
        return username.length() == 5 && username.contains("_");
    }

    // Check password complexity
    public static boolean checkPasswordComplexity(String password) {
        // Password must:
        // - Have at least 8 characters
        // - Contain an uppercase letter
        // - Contain a lowercase letter
        // - Contain a number
        // - Contain a special character
        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }

    // Check cellphone number
    public static boolean checkCellphoneNumber(String number) {
        // Number must start with +27 followed by exactly 9 digits
        return number.matches("^\\+27[0-9]{9}$");
    }

    // Register user
    public static void registerUser() {
        boolean registrationSuccessful = false;

        while (!registrationSuccessful) {

            System.out.print("Enter your username: ");
            username = input.nextLine();

            System.out.print("Enter your password: ");
            password = input.nextLine();

            System.out.print("Enter your cellphone number (+27): ");
            cellphone = input.nextLine();

            // Check all details
            boolean usernameCorrect = checkUserName(username);
            boolean passwordCorrect = checkPasswordComplexity(password);
            boolean phoneCorrect = checkCellphoneNumber(cellphone);

            // Register if all details are valid
            if (usernameCorrect && passwordCorrect && phoneCorrect) {

                registeredUsername = username;
                registeredPassword = password;
                registeredCellphone = cellphone;

                System.out.println("User registered successfully.");
                registrationSuccessful = true;

            } else {
                System.out.println(
                    "Registration failed. Please ensure:"
                    + "\n- Username contains an underscore and is exactly 5 characters."
                    + "\n- Password is at least 8 characters and contains uppercase, lowercase, number and special character."
                    + "\n- Cellphone number starts with +27 followed by exactly 9 digits."
                );
            }
        }
    }

    // Login user
    public static boolean loginUser(String loginUsername, String loginPassword) {

        return loginUsername.equals(registeredUsername)
                && loginPassword.equals(registeredPassword);
    }

    // Return login status
    public static String returnLoginStatus(boolean loginSuccessful) {

        if (loginSuccessful) {
            return "Welcome " + registeredUsername 
                    + " , it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Main method
    public static void main(String[] args) {

        // Register the user
        registerUser();

        // Login
        System.out.println("\n--- Login ---");

        System.out.print("Enter username to login: ");
        String loginUsernameInput = input.nextLine();

        System.out.print("Enter password to login: ");
        String loginPasswordInput = input.nextLine();

        // Check login
        boolean loginSuccessful =
                loginUser(loginUsernameInput, loginPasswordInput);

        // Display login status
        System.out.println(returnLoginStatus(loginSuccessful));

        // Close Scanner
        input.close();
    }
}
