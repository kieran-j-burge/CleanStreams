package CleanStreams.Services.Impl;

import CleanStreams.DAO.AccountDAO;
import CleanStreams.DTO.Account;
import CleanStreams.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account findByEmailAndPassword(String email, String password) {
        try {
            return accountDAO.findAccount(email, password);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Boolean createAccount(String email, String password, Integer foreignKey) {
        if (!accountDAO.checkAccountExists(email)){
            accountDAO.createAccount(email, password, foreignKey);
            return true;
        }
        return false;
    }

    //    https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
    @Override
    public String generatePassword(Integer length) {
        String available = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ )
            sb.append( available.charAt( random.nextInt(available.length()) ) );
        return sb.toString();
    }

    @Override
    public Boolean changePassword(String currentPassword, String newPassword, Integer accountId) {
        Account account = accountDAO.findAccountById(accountId);

        if (account.getPassword().equalsIgnoreCase(currentPassword)){
            accountDAO.changePassword(newPassword, accountId);
            return true;
        }

        return false;
    }
}