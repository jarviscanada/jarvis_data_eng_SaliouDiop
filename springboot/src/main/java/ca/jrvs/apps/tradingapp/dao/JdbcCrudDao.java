package ca.jrvs.apps.tradingapp.dao;

import ca.jrvs.apps.tradingapp.model.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);
    abstract public JdbcTemplate getJdbcTemplate();
    abstract public SimpleJdbcInsert getSimpleJdbcInsert();
    abstract public String getTableName();
    abstract public String getIdColumnName();
    abstract public Class<T> getEntityClass();

    @Override
    public <S extends T> S save(S entity) {
        if(existsById(entity.getId())) {
            if (updateOne(entity) != 1) {
                logger.error("updateOne failed");
                throw new RuntimeException("updateOne failed");
            }
        } else {
            addOne(entity);
        }
        return entity;
    }

    private <S extends T> void addOne(S entity) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(entity);
        Number newId = getSimpleJdbcInsert().executeAndReturnKey(params);
        entity.setId(newId.intValue());
    }

    abstract public int updateOne(T entity);

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<T> findById(Integer id) {
        Optional<T> optional = Optional.empty();
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try {
            optional = Optional.ofNullable(
                    getJdbcTemplate()
                            .queryForObject(
                                    sql,
                                    BeanPropertyRowMapper.newInstance(getEntityClass()),
                                    id
                            )
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            logger.debug("cannot find entity with id: " + id);
        }
        return optional;
    }

    @Override
    public boolean existsById(Integer integer) {
        return findById(integer).isPresent();
    }

    @Override
    public Iterable<T> findAll() {
        String sql = "SELECT * FROM " + getTableName();
        return getJdbcTemplate().query(sql, BeanPropertyRowMapper.newInstance(getEntityClass()));
    }

    @Override
    public Iterable<T> findAllById(Iterable<Integer> ids) {
        List<T> allEntities = new ArrayList<>();
        for (Integer id : ids) {
            allEntities.add(findById(id).get());
        }
        return allEntities;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM " + getTableName();
        return getJdbcTemplate().queryForObject(sql, Long.class);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        getJdbcTemplate().update(sql, id);
    }

    @Override
    public void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM " + getTableName();
        getJdbcTemplate().update(sql);
    }
}
