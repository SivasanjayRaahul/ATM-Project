package atm.repository;

import atm.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankRepoImpl implements BankRepo {
    Logger logger = Logger.getLogger("BankRepoImpl");

    @Override
    public boolean isValidAccount(String accountNumber) {

        ResultSet result;
        boolean isValidAccount = false;
        try {
            PreparedStatement selectCredentials = Database.getConnection().prepareStatement("select * from user where Account_No=?");
            selectCredentials.setString(1, accountNumber);
            result = selectCredentials.executeQuery();
            isValidAccount = result.next();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return isValidAccount;
    }

    @Override
    public boolean isValidAccountType(String accountNumber, String accountType) {
        ResultSet result;
        boolean isValidAccountType = false;
        try {
            PreparedStatement selectCredentials = Database.getConnection().prepareStatement("select * from user where Account_No=? and Account_Type=?");
            selectCredentials.setString(1, accountNumber);
            selectCredentials.setString(2, accountType);
            result = selectCredentials.executeQuery();
            isValidAccountType = result.next();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return isValidAccountType;

    }

    @Override
    public boolean isPinValidate(String accountNumber, int pin) {
        ResultSet result;
        boolean isPinValidate = false;
        try {
            PreparedStatement selectCredentials = Database.getConnection().prepareStatement("select * from user where Account_No=? and pin=?");
            selectCredentials.setString(1, accountNumber);
            selectCredentials.setInt(2, pin);
            result = selectCredentials.executeQuery();
            isPinValidate = result.next();
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return isPinValidate;
    }

    //TODO balance

}
