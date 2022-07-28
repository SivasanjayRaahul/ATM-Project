package atm.modelTest;

import atm.model.Account;
import atm.model.User;
import atm.model.bank.HDFCBank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnZeroBankAccountsForNewUser() {

        User user = new User("Siva", "ssr@gmail.com", 21);
        String customerId = user.getCustomerId();

        List<Account> bankAccounts = user.getBankAccounts(customerId);

        assertEquals(0, bankAccounts.size());

    }

    @Test
    void shouldCreateABankAccountWithZeroBalanceWhenAccountIsNewlyCreated() {
        User user = new User("Siva", "ssr@gmail.com", 21);
        String customerId = user.getCustomerId();
        user.createAccount(new HDFCBank(), "SAVINGS");
        List<Account> bankAccounts = user.getBankAccounts(customerId);

        Account account = bankAccounts.get(0);

        assertEquals(0, account.checkBalance());

    }

}