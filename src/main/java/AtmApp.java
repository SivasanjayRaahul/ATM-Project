import atm.model.Card;
import atm.model.User;
import atm.model.bank.HDFCBank;

public class AtmApp {

    public static void main(String[] args) {

        User user=new User("sruthi","sruthi@gmail.com",21);
        user.createAccount(new HDFCBank(),"Savings");

        //accounts.createcard(num,pin,acc)

    }
}
