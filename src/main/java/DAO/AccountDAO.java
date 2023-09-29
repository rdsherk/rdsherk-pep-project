package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDAO {
    
    public Account getAccount(int id) {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }

    public boolean login(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        return false;
    }

    public Account createAccount(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }
}
