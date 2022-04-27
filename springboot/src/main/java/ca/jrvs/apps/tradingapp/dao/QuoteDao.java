package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME);
    }

    @Override
    public Quote  save(Quote quote){
        if (existsById(quote.getTicker())) {
            int updateRowNo = updateOne(quote);
            if (updateRowNo != 1) {
                throw new DataRetrievalFailureException("Failed to update quote");
            }
        } else {
            addOne(quote);
        }
        return quote;
    }

    private int updateOne(Quote quote) {
        String sql = "" +
                "UPDATE " + TABLE_NAME + " " +
                "SET last_price = ?, bid_price = ?, ask_price = ?, bid_size = ?, ask_size = ? " +
                "WHERE ticker = ?";
        return jdbcTemplate.update(sql, makeUpdateValues(quote));
    }

    private Object[] makeUpdateValues(Quote quote) {
        return new Object[]{
                quote.getLastPrice(),
                quote.getBidPrice(),
                quote.getAskPrice(),
                quote.getBidSize(),
                quote.getAskSize(),
                quote.getTicker()
        };
    }

    private void addOne(Quote quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = jdbcInsert.execute(parameterSource);
        logger.info("Inserted {} rows", row);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert quote", 1, row);
        }
    }

    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> quotes) {
        for (Quote quote : quotes) {
            save(quote);
        }
        return quotes;
    }

    @Override
    public Optional<Quote> findById(String s) {
        String sql = "" +
                "SELECT * " +
                "FROM " + TABLE_NAME + " " +
                "WHERE ticker = ?";
        Quote quote = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Quote.class), s);
        return Optional.ofNullable(quote);
    }

    @Override
    public boolean existsById(String s) {
        String sql = "" +
                "SELECT COUNT(*) " +
                "FROM " + TABLE_NAME + " " +
                "WHERE ticker = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, s) > 0;
    }

    @Override
    public List<Quote> findAll() {
        String sql = "" +
                "SELECT * " +
                "FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Quote.class));
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> strings) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        String sql = "" +
                "SELECT COUNT(*) " +
                "FROM " + TABLE_NAME;
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void deleteById(String s) {
        String sql = "" +
                "DELETE FROM " + TABLE_NAME + " " +
                "WHERE ticker = ?";
        jdbcTemplate.update(sql, s);
    }

    @Override
    public void delete(Quote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Quote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        String sql = "" +
                "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.update(sql);
    }
}
