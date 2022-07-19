package atm.model;

import atm.model.bank.Bank;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String customerId;
    private final String name;
    private final String emailId;
    private int age;
    private List<Account> bankAccounts = new ArrayList<>();


    public User(String name, String emailId, int age) {
        this.customerId = name.substring(4) + emailId.substring(4) + String.valueOf(age);
        this.name = name;
        this.emailId = emailId;
        this.age = age;
    }

    public void createAccount(Bank bank, String accountType) {
        Account account = new Account(String.valueOf(Math.random() * 100000000000000L), accountType, 0, name, bank, customerId);
        bankAccounts.add(account);
    }

}
