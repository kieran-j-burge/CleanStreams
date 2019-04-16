package CleanStreams.DAO;

import CleanStreams.DTO.Account;

public interface AccountDAO {
    Account findAccount(String email, String password);
    void createAccount(String email, String password, Integer foreignKey);
    Boolean checkAccountExists(String email);
    void changePassword(String password, Integer accountId);
    Account findAccountById(Integer accountId);
}