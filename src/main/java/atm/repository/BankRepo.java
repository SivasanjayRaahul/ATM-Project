package atm.repository;

public interface BankRepo {
    boolean isValidAccount(String accountNumber);

    boolean isValidAccountType(String accountNumber, String accountType);

    boolean isPinValidate(String accountNumber, int pin);

}
