package atm.model;

import atm.model.bank.Bank;
import atm.model.bank.HDFCBank;

import java.util.ArrayList;
import java.util.List;

import static atm.constant.Constants.SCANNER;

public class BankGateway {
    private final List<User> users = new ArrayList<>();

    public String createUser(String name, String emailId, int age) {
        User user = new User(name, emailId, age);
        users.add(user);
        return user.getCustomerId();
    }

    public void createBankAccount(User user, Bank bank, String accountType) {
        if (users.contains(user))
            user.createAccount(bank, accountType);
    }

    public User getUser(String customerId){
        for(User user:this.users){
            if(user.getCustomerId().equals(customerId))
                return user;
        }
        return null;
    }

    public static void main(String[] args) {
        BankGateway bankGateway = new BankGateway();
        String customerId = bankGateway.createUser("sru", "sru@gmail.com", 21);
        String customerIdForBank = SCANNER.next();
        User user = bankGateway.getUser(customerIdForBank);
        bankGateway.createBankAccount(user,new HDFCBank(),"Savings");
    }

}
