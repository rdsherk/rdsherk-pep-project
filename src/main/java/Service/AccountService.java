package Service;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account getAccount(int id) {
        return accountDAO.getAccount(id);
    }

    public Account login(Account account) {
        return accountDAO.login(account);
    }

    public Account createAccount(String username, String password) {
        return accountDAO.createAccount(username, password);
    }
}
