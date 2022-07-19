package atm.model;

public class Card {

    private final String number;
    private final int pin;
    private final Account account;


    public Card(String number, String holderName, Account account, int pin) {
        this.number = number;
        this.account = account;
        this.pin = pin;
    }

    public float checkBalance(){
        return account.checkBalance();
    }
}
