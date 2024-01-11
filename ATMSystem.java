import java.util.Scanner;

class BankAccount {

  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public boolean withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      return true;
    } else {
      return false;
    }
  }
}

class ATM {

  private BankAccount bankAccount;

  public ATM(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public void displayOptions() {
    System.out.println("ATM Menu:");
    System.out.println("1. Withdraw");
    System.out.println("2. Deposit");
    System.out.println("3. Check Balance");
    System.out.println("4. Exit");
  }

  public void withdraw(double amount) {
    if (bankAccount.withdraw(amount)) {
      System.out.println(
        "Withdrawal successful. Remaining balance: $" + bankAccount.getBalance()
      );
    } else {
      System.out.println(
        "Insufficient funds or invalid amount for withdrawal."
      );
    }
  }

  public void deposit(double amount) {
    bankAccount.deposit(amount);
    System.out.println(
      "Deposit successful. New balance: $" + bankAccount.getBalance()
    );
  }

  public void checkBalance() {
    System.out.println("Current balance: $" + bankAccount.getBalance());
  }
}

public class ATMSystem {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter initial balance: ");
    double initialBalance = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    BankAccount userAccount = new BankAccount(initialBalance);
    ATM atm = new ATM(userAccount);

    while (true) {
      atm.displayOptions();
      System.out.print("Enter your choice (1-4): ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      switch (choice) {
        case 1:
          System.out.print("Enter withdrawal amount: $");
          double withdrawalAmount = scanner.nextDouble();
          scanner.nextLine(); // Consume the newline character
          atm.withdraw(withdrawalAmount);
          break;
        case 2:
          System.out.print("Enter deposit amount: $");
          double depositAmount = scanner.nextDouble();
          scanner.nextLine(); // Consume the newline character
          atm.deposit(depositAmount);
          break;
        case 3:
          atm.checkBalance();
          break;
        case 4:
          System.out.println("Exiting ATM. Goodbye!");
          System.exit(0);
          break;
        default:
          System.out.println(
            "Invalid choice. Please enter a valid option (1-4)."
          );
      }
    }
  }
}
