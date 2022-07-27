package atm.modelTest;

import atm.model.Account;
import atm.model.User;
import atm.model.bank.HDFCBank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    static Account account;

    @BeforeAll
    static void beforeAll() {
        User user = new User("Siva", "ssr@gmail.com", 21);
        String customerId = user.getCustomerId();
        user.createAccount(new HDFCBank(), "SAVINGS");
        List<Account> bankAccounts = user.getBankAccounts(customerId);
        account = bankAccounts.get(0);
    }

    @Test
    void shouldReturnFiftyRupeesAsBalanceWhenFiftyRupeesIsDepositedInitially() {

        account.deposit(50);

        assertEquals(50.0, account.checkBalance());
    }


    @Test
    void shouldReturnFiftyRupeesWhenFiftyRupeesIsWithdrawn() {

        account.deposit(100);

        account.withdraw(50);

        assertEquals(50.0, account.checkBalance());
    }

    @Test
    void shouldReturnHDFCAsBankForTheAccount() {
        assertEquals(account.getBank().getName(), "HDFCBANK");
    }

    @Test
    void shouldReturnNoCardForNewlyCreatedAccount() {

        assertNull(account.getCard());
    }

    @Test
    void shouldReturnCardWhenCardIsGeneratedForTheAccount() {

        account.createCard(1234);

        assertNotNull(account.getCard());
    }

}