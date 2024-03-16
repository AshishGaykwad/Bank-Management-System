package Bank_Project;


import java.util.*;

class BankAccount {
      int accountNumber;
      String name;
      String mobileNumber;
      String password;
      String address;
      double bankBalance;
      List<String> passbook;

    public BankAccount(int accountNumber, String name, String mobileNumber, String password, String address, double bankBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.address = address;
        this.bankBalance = bankBalance;
        this.passbook = new ArrayList<>();
    }

   

    public void deposit(double amount) {
        bankBalance = bankBalance + amount;
        passbook.add("Deposit: +" + amount);
    }

    public boolean withdraw(double amount, String inputPassword) {
        if (inputPassword.equals(password) && bankBalance >= amount) {
            bankBalance = bankBalance - amount;
            passbook.add("Withdraw: -" + amount);
            return true;
        }
        return false;
    }
}

public class BankingSystem {
    private static List<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int lastAccountNumber = 1000; // Starting account number

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create new user");
            System.out.println("2. Deposit amount");
            System.out.println("3. Withdraw amount");
            System.out.println("4. Show transaction history");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createNewUser();
                    break;
                case 2:
                    depositAmount();
                    break;
                case 3:
                    withdrawAmount();
                    break;
                case 4:
                    showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void createNewUser() {
        System.out.println("Creating new user...");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter initial bank balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BankAccount account = new BankAccount(++lastAccountNumber, name, mobileNumber, password, address, initialBalance);
        accounts.add(account);
        System.out.println("User created successfully. Account number: " + account.accountNumber);
    }

    private static void depositAmount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Amount deposited successfully. New balance: " + account.bankBalance);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdrawAmount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount, password)) {
                System.out.println("Amount withdrawn successfully. New balance: " + account.bankBalance);
            } else {
                System.out.println("Withdrawal failed! Incorrect password or insufficient balance.");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void showTransactionHistory() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            List<String> passbook = account.passbook;
            System.out.println("Transaction History for Account Number " + accountNumber + ":");
            for (String transaction : passbook) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    private static BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.accountNumber == accountNumber) {
                return account;
            }
        }
        return null;
    }
}