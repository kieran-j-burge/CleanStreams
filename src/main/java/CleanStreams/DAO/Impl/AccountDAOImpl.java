package CleanStreams.DAO.Impl;


import CleanStreams.DAO.AccountDAO;
import CleanStreams.DTO.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccount(String email, String password) {
        return jdbcTemplate.queryForObject("SELECT * FROM account WHERE username = ? AND password = ?",
                new Object[]{email, password},
                (rs, rowNum) -> new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("permissions")
                ));

    }

    @Override
    public void createAccount(String email, String password, Integer foreignKey) {
        jdbcTemplate.update("INSERT INTO account (username, password, fk_type) VALUES (?, ?, ?)", email, password, foreignKey);
    }

    @Override
    public Boolean checkAccountExists(String email) {
        try {
            jdbcTemplate.queryForObject("SELECT * FROM account WHERE username = ?",
                    new Object[]{email},
                    (rs, rowNum) -> new Account(
                            rs.getInt("account_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("permissions")
                    ));

            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void changePassword(String password, Integer accountId) {
        jdbcTemplate.update("UPDATE account SET password = ? WHERE id = ?", password, accountId);
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return jdbcTemplate.queryForObject("SELECT * FROM account WHERE id = ?",
                new Object[]{accountId},
                (rs, rowNum) -> new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("permissions")
                ));

    }
}
