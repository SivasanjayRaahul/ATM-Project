package atm.model;

import atm.helper.Encryption;

import java.util.Objects;

public class Card {

    private final String number;
    private final int pin;
    private final Account account;
    private final String encryptedPin;
    private boolean status;
    private int attempt;

    public Card(String number, Account account, int pin) {
        this.number = number;
        this.account = account;
        this.pin = pin;
        this.encryptedPin = Encryption.getMd5(String.valueOf(pin));
        this.status = true;
        this.attempt = 0;
    }

    public Account getAccount() {
        return account;
    }

    public boolean validatePin(int pin) {
        return Objects.equals(encryptedPin, Encryption.getMd5(String.valueOf(pin)));
    }

    public float checkBalance() {
        return account.checkBalance();
    }

    public void block() {
        status = false;
    }

    public boolean isStatus() {
        return status;
    }

    public int getPinAttempt() {
        return attempt;
    }

    public void pinAttempt() {
        this.attempt++;
    }


}
