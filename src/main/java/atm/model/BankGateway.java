package atm.model;

import atm.model.bank.AxisBank;
import atm.model.bank.Bank;
import atm.model.bank.HDFCBank;

import java.util.ArrayList;
import java.util.List;

import static atm.constant.Constants.SCANNER;

public class BankGateway {
    private final List<User> users = new ArrayList<>();
    private final static BankGateway bankGateway = new BankGateway();


    public String createUser(String name, String emailId, int age) {
        User user = new User(name, emailId, age);
        users.add(user);
        return user.getCustomerId();
    }

    public void createBankAccount(User user, Bank bank, String accountType) {
        if (users.contains(user))
            user.createAccount(bank, accountType);
    }

    public User getUser(String customerId) {
        for (User user : this.users) {
            if (user.getCustomerId().equals(customerId))
                return user;
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("1)Create an account");
        System.out.println("2)Create a bank account");
        System.out.println("3)Create ATM card");
        int choice = SCANNER.nextInt();
        if (choice == 1) {

            System.out.println("Enter the name");
            String name = SCANNER.next();

            System.out.println("Enter the mail address");
            String mail = SCANNER.next();

            System.out.println("Enter the age");
            int age = SCANNER.nextInt();

            System.out.println("Your Customer Id is: " + bankGateway.createUser(name, mail, age));
            main(null);
        } else if (choice == 2) {
            System.out.println("Enter the customerId");
            String customerId = SCANNER.next();

            User user = bankGateway.getUser(customerId);
            System.out.println("Select Bank name:\n1)HDFC\n2)AXIS");
            Bank bank;
            int bankChoice = SCANNER.nextInt();
            if (bankChoice == 1)
                bank = new HDFCBank();
            else
                bank = new AxisBank();


            System.out.println("Select Account Type:\n1)Savings\n2)Current");
            String accountType;
            int accountTypeChoice = SCANNER.nextInt();
            if (accountTypeChoice == 1)
                accountType = "Savings";
            else
                accountType = "Current";

            bankGateway.createBankAccount(user, bank, accountType);
            main(null);
        } else {
            System.out.println("Enter the customerId");
            String customerId = SCANNER.next();

            User user = bankGateway.getUser(customerId);
            List<Account> bankAccounts = user.getBankAccounts(user.getCustomerId());
            if (bankAccounts.size() != 0) {
                System.out.println("Your Bank Accounts are:\n");
                for (Account bankAccount : bankAccounts) {
                    System.out.println(bankAccount.getNumber(user.getCustomerId()));
                }
            } else
                System.out.println("No Bank Accounts Found");

            System.out.println("Select account number to generate ATM card");
            int cardChoice = SCANNER.nextInt();
            if (cardChoice > 0 && cardChoice <= bankAccounts.size()) {
                Account account = bankAccounts.get(cardChoice - 1);
                System.out.println("Enter the Pin");
                int pin = SCANNER.nextInt();
                account.createCard(pin);
            } else
                System.out.println("Enter proper Account number ordered");
            main(null);
        }

    }

}
