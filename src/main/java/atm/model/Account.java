package atm.model;

import atm.model.bank.Bank;

public class Account implements Transaction {
    private final String number;
    private final String accountType;
    private float balance;
    private final String holderName;
    private final Bank bank;
    private final String customerId;


    public Account(String number, String accountType, int balance, String holderName, Bank bank, String customerId) {
        this.number = number;
        this.accountType = accountType;
        this.balance = balance;
        this.holderName = holderName;
        this.bank = bank;
        this.customerId = customerId;
    }

    public float checkBalance() {
        return balance;
    }

    @Override
    public void withdraw(int amount) {
        if (amount < balance) {
            balance -= amount;
        }

    }

    @Override
    public void deposit(int amount) {
        balance += amount;
    }
}
