package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.domain.SecurityOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class SecurityDao extends JdbcCrudDao<SecurityOrder> {

    private static final String TABLE_NAME = "security_order";
    private static final String ID_COLUMN_NAME = "id";
    private static final String ACCOUNT_ID = "account_id";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public SecurityDao(DataSource dataSource) {
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
    public Class<SecurityOrder> getEntityClass() {
        return SecurityOrder.class;
    }

    @Override
    public int updateOne(SecurityOrder entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteByAccountId(Integer accountId) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ACCOUNT_ID + " = ?";
        jdbcTemplate.update(sql, accountId);
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.update(sql);
    }

    @Override
    public void delete(SecurityOrder securityOrder) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " = ?";
        jdbcTemplate.update(sql, securityOrder.getId());
    }
}
