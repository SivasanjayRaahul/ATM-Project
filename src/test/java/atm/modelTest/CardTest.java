package atm.modelTest;

import atm.model.Account;
import atm.model.Card;
import atm.model.User;
import atm.model.bank.HDFCBank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CardTest {
    static Account account;
    static Card card;

    @BeforeAll
    static void beforeAll() {
        User user = new User("Siva", "ssr@gmail.com", 21);
        String customerId = user.getCustomerId();
        user.createAccount(new HDFCBank(), "SAVINGS");
        List<Account> bankAccounts = user.getBankAccounts(customerId);
        account = bankAccounts.get(0);
        account.createCard(1234);
        card = account.getCard();
    }

    @Test
    void shouldReturnSameBalanceAsInAccountWhenCreatingCardInitially() {

        assertEquals(account.checkBalance(), card.checkBalance());
    }

    @Test
    void shouldReturnTrueAsCardStatusWhenCardIsUnblock() {
        assertTrue(card.isStatus());
    }

    @Test
    void shouldReturnFalseAsCardStatusWhenCardIsBlock() {
        card.block();
        assertFalse(card.isStatus());
    }

    @Test
    void shouldReturnTrueWhenPinIsEnteredCorrectly() {
        assertTrue(card.validatePin(1234));
    }

    @Test
    void shouldReturnFalseWhenPinIsNotEnteredCorrectly() {
        assertFalse(card.validatePin(1111));
    }
}
