
import java.util.Scanner;

interface Transactable {
    void deposit(double amount);
    void withdraw(double amount);
}

abstract class Account implements Transactable {
    String accountNumber;
    double balance;
    String owner;

    Account(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        checkBalance();
    }

    public void transferFunds(Account toAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Insufficient funds for transfer.");
            return;
        }

        this.balance -= amount;
        toAccount.balance += amount;

        System.out.println("Transferred " + amount + " to " + toAccount.owner);
        checkBalance();
    }
}

class CheckingAccount extends Account {
    CheckingAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        }
        checkBalance();
    }
}

class SavingsAccount extends Account {
    static final double withdrawalLimit = 500;

    SavingsAccount(String accountNumber, String owner, double balance) {
        super(accountNumber, owner, balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else if (amount > withdrawalLimit) {
            System.out.println("Withdrawal exceeds limit of " + withdrawalLimit);
        } else {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
        }
        checkBalance();
    }
}

class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account[] accounts = {
            new CheckingAccount("1001", "Alice", 1500.0),
            new SavingsAccount("1001", "Alice", 2000.0),
            new CheckingAccount("1003", "Charlie", 1800.0),
            new SavingsAccount("1004", "David", 2200.0),
            new CheckingAccount("1005", "Eve", 1700.0)
        };

        Account currentAccount = null;
        while (currentAccount == null) {
            System.out.println("Please enter your account number:");
            String accountNumber = scanner.nextLine();

            Account checking = null;
            Account savings = null;
            
            for (Account acc : accounts) {
                if (acc.accountNumber.equals(accountNumber)) {
                    if (acc instanceof CheckingAccount) {
                        checking = acc;
                    } else if (acc instanceof SavingsAccount) {
                        savings = acc;
                    }
                }
            }

            if (checking == null && savings == null) {
                System.out.println("Account not found. Please try again or enter 'exit' to quit.");
                if (accountNumber.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    return;
                }
            } else if (checking != null && savings != null) {
                System.out.println("Choose account type:\n1. Checking\n2. Savings");
                currentAccount = scanner.nextInt() == 1 ? checking : savings;
                scanner.nextLine(); 
            } else {
                currentAccount = checking != null ? checking : savings;
            }
        }

        System.out.println("Welcome, " + currentAccount.owner);

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentAccount.checkBalance();
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter recipient account number: ");
                    String recipientAccountNumber = scanner.nextLine();

                    Account recipientAccount = null;
                    for (Account acc : accounts) {
                        if (acc.accountNumber.equals(recipientAccountNumber)) {
                            recipientAccount = acc;
                            break;
                        }
                    }

                    if (recipientAccount == null) {
                        System.out.println("Recipient account not found.");
                    } else {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        currentAccount.transferFunds(recipientAccount, transferAmount);
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
