package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@Repository

public class AccountDao extends JdbcCrudDao<Account> {

    private static final String TABLE_NAME = "account";
    private static final String ID_COLUMN_NAME = "id";
    private static final String TRADER_ID = "trader_id";
    private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public AccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(ID_COLUMN_NAME);
    }
    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return jdbcInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN_NAME;
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public int updateOne(Account account) {
        // update the amount
        String sql = "UPDATE " + TABLE_NAME + " SET amount = ? WHERE " + ID_COLUMN_NAME + " = ?";
        return jdbcTemplate.update(sql, account.getAmount(), account.getId());
    }

    public Optional<Account> findByTraderId(Integer traderId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + TRADER_ID + " = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getEntityClass(), traderId));
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> {
            if (entity.getId() == null) {
                entity.setId(jdbcInsert.executeAndReturnKey(getInsertValues(entity)).intValue());
            } else {
                updateOne(entity);
            }
        });
        return entities;
    }

    private <S extends Account> Map<String,?> getInsertValues(S entity) {
        return Map.of(TRADER_ID, entity.getTraderId(), "amount", entity.getAmount());
    }

    @Override
    public void delete(Account account){
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?";
        jdbcTemplate.update(sql, account.getId());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.update(sql);
    }
}
