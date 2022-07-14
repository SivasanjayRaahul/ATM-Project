package atm.repository;

public interface BankRepo {
    boolean isValidAccountNumber(String accountNumber);

    boolean isValidAccountType(String accountNumber, String accountType);

    boolean isPinValidate(String accountNumber, int pin);

    int balance(String accountNumber);

}
