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

    private String createUser(String name, String emailId, int age) {
        User user = new User(name, emailId, age);
        users.add(user);
        return user.getCustomerId();
    }

    private void createBankAccount(User user, Bank bank, String accountType) {
        if (users.contains(user))
            user.createAccount(bank, accountType);
    }

    private User getUser(String customerId) {
        for (User user : this.users) {
            if (user.getCustomerId().equals(customerId))
                return user;
        }
        return null;
    }

    private boolean isPinValidate(Card card, int pin) {
        return card.validatePin(pin);
    }

    private void atmActivities(Card card) {
        System.out.println("Enter Pin:");
        int pin = SCANNER.nextInt();
        if (isPinValidate(card, pin)) {
            System.out.println("Select an option:\n1) Cash withdrawal \n2) Balance Enquiry\n3) Deposit\n4) Exit");
            int option = SCANNER.nextInt();
            if (option == 1) {
                System.out.println("Enter the withdrawal amount:");
                int amount = SCANNER.nextInt();
                if (amount <= card.checkBalance()) {
                    card.getAccount().withdraw(amount);
                    System.out.println("Amount withdrawal successful. Remaining Balance: " + card.checkBalance());
                    main(null);
                } else {
                    System.out.println("Balance not sufficient");
                    main(null);
                }
            } else if (option == 2) {
                System.out.println("Balance Amount: " + card.checkBalance());
                main(null);
            } else if (option == 3) {
                System.out.println("Enter the amount to be deposited:");
                int amount = SCANNER.nextInt();
                card.getAccount().deposit(amount);
                System.out.println("Amount deposited");
                main(null);

            } else
                main(null);
        } else {
            if (card.getPinAttempt() <= 1) {
                card.pinAttempt();
                System.out.println("Incorrect Pin");
                atmActivities(card);
            }
            card.block();
            System.out.println("Sorry your card is blocked :( ");
            main(null);
        }
    }

    public static void main(String[] args) {

        System.out.println("1)Create an User account");
        System.out.println("2)Create a bank account");
        System.out.println("3)Create ATM card");
        System.out.println("4)Use ATM");

        int choice = SCANNER.nextInt();
        if (choice == 1) {

            System.out.println("Enter the name");
            String name = SCANNER.next();

            System.out.println("Enter the mail address");
            String mail = SCANNER.next();

            System.out.println("Enter the age");
            int age = SCANNER.nextInt();

            System.out.println("Your Customer Id is: " + bankGateway.createUser(name, mail, age));
            System.out.println("User Account created successfully");
            main(null);
        } else if (choice == 2) {
            System.out.println("Enter the customerId");
            String customerId = SCANNER.next();

            User user;
            if (bankGateway.getUser(customerId) != null) {
                user = bankGateway.getUser(customerId);
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
                System.out.println("Bank Account created successfully");
                main(null);
            } else {
                System.out.println("No UserId found");
                main(null);
            }

        } else if (choice == 3) {
            System.out.println("Enter the customerId");
            String customerId = SCANNER.next();

            User user;
            if (bankGateway.getUser(customerId) != null) {
                user = bankGateway.getUser(customerId);
                List<Account> bankAccounts = user.getBankAccounts(user.getCustomerId());
                bankAccounts.stream().filter(account -> account.getCard() != null);
                if (bankAccounts.size() != 0) {
                    System.out.println("Your Bank Accounts are:");
                    for (int account = 0; account < bankAccounts.size(); account++) {
                        System.out.println(account + 1 + ") " + bankAccounts.get(account).getBank().getName());
                    }
                } else {
                    System.out.println("No Bank Accounts Found");
                    main(null);
                }

                System.out.println("Select Bank name to generate ATM card");
                int cardChoice = SCANNER.nextInt();
                if (cardChoice > 0 && cardChoice <= bankAccounts.size()) {
                    Account account = bankAccounts.get(cardChoice - 1);
                    System.out.println("Set 4-Digit Pin");
                    int pin = SCANNER.nextInt();
                    if (String.valueOf(pin).length() == 4)
                        account.createCard(pin);
                    else {
                        System.out.println("Enter a 4-Digit Pin");
                        main(null);
                    }
                } else {
                    System.out.println("Select proper Bank");
                    main(null);
                }
                System.out.println("ATM Card created successfully");
                main(null);
            } else {
                System.out.println("No UserId found");
                main(null);
            }


        } else {
            System.out.println("Enter the customerId");
            String customerId = SCANNER.next();

            User user;
            if (bankGateway.getUser(customerId) != null) {
                user = bankGateway.getUser(customerId);
                List<Account> bankAccounts = user.getBankAccounts(user.getCustomerId());
                if (bankAccounts.size() != 0) {
                    System.out.println("Your Bank Accounts are:");
                    for (int account = 0; account < bankAccounts.size(); account++) {
                        System.out.println(account + 1 + ") " + bankAccounts.get(account).getBank().getName());
                    }
                } else {
                    System.out.println("No Bank Accounts Found");
                    main(null);
                }
                int cardChoice = SCANNER.nextInt();
                if (cardChoice > 0 && cardChoice <= bankAccounts.size()) {
                    Account account = bankAccounts.get(cardChoice - 1);
                    if (account.getCard() != null) {
                        Card card = account.getCard();
                        if (card.isStatus())
                            bankGateway.atmActivities(card);
                        else {
                            System.out.println("Sorry your card is Blocked");
                            main(null);
                        }
                    } else {
                        System.out.println("No cards exist for this account");
                        main(null);
                    }

                } else {
                    System.out.println("Select proper Bank Name");
                    main(null);
                }
            } else {
                System.out.println("No UserId found");
                main(null);
            }
        }
    }
}
