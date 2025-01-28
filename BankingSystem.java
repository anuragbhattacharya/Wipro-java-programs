import java.util.ArrayList;
import java.util.List;

// Base Account Class
abstract class Account {
    private String accountNumber;
    private String accountHolder;
    protected double balance;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);

    public boolean transfer(Account toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }
}

// Savings Account Class
class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited to Savings Account " + getAccountNumber());
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= MINIMUM_BALANCE) {
            balance -= amount;
            System.out.println(amount + " withdrawn from Savings Account " + getAccountNumber());
            return true;
        }
        System.out.println("Withdrawal failed. Insufficient balance.");
        return false;
    }
}

// Current Account Class
class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000.0;

    public CurrentAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited to Current Account " + getAccountNumber());
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println(amount + " withdrawn from Current Account " + getAccountNumber());
            return true;
        }
        System.out.println("Withdrawal failed. Overdraft limit exceeded.");
        return false;
    }
}

// Customer Class
class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void listAccounts() {
        System.out.println("Accounts for " + name + ":");
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }
}

// Main Banking System Class
public class BankingSystem {
    public static void main(String[] args) {
        // Create Customers
        Customer john = new Customer("John Doe");
        Customer jane = new Customer("Jane Smith");

        // Create Accounts
        SavingsAccount johnSavings = new SavingsAccount("S001", "John Doe", 1000);
        CurrentAccount johnCurrent = new CurrentAccount("C001", "John Doe", 2000);

        SavingsAccount janeSavings = new SavingsAccount("S002", "Jane Smith", 1500);

        // Add Accounts to Customers
        john.addAccount(johnSavings);
        john.addAccount(johnCurrent);

        jane.addAccount(janeSavings);

        // Transactions
        johnSavings.deposit(500);
        johnCurrent.withdraw(300);
        johnSavings.transfer(janeSavings, 200);

        // Display Account Details
        john.listAccounts();
        jane.listAccounts();
    }
}
