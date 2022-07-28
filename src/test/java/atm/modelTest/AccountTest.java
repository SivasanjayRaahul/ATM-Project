package atm.modelTest;

import atm.model.Account;
import atm.model.User;
import atm.model.bank.HDFCBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    static Account account;

    @BeforeEach
    public void beforeEach() {
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
    void shouldReturnFiftyRupeesAsBalanceWhenFiftyRupeesIsWithdrawnWithAccountHavingHundredInIt() {

        account.deposit(100);

        account.withdraw(50);

        assertEquals(50.0, account.checkBalance());
    }

    @Test
    void shouldReturnSameBalanceWhenWithdrawalAmountIsGreaterThanBalance() {

        account.deposit(10);

        account.withdraw(50);

        assertEquals(10.0, account.checkBalance());
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