package atm.model;

public interface Transaction {
    void withdraw(int amount);

    void deposit(int amount);

}
