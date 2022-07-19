package atm.model;

public class Account {
    private final String number;
    private final String accountType;
    private final int balance;
    private final String holderName;

    public Account(String number, String accountType, int balance, String holderName) {
        this.number = number;
        this.accountType = accountType;
        this.balance = balance;
        this.holderName = holderName;
    }

    public String getNumber() {
        return number;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getBalance() {
        return balance;
    }

    public String getHolderName() {
        return holderName;
    }

}
