package ca.jrvs.apps.twitter.dao;

import java.io.IOException;

public interface CrdDao <T, ID> {
    /**
     * Create a new entity
     * @param entity the entity to create
     * @return the created entity
     */
    T create(T entity) throws IOException;

    /**
     * Find an entity by id
     * @param id the id of the entity to find
     * @return the found entity
     */
    T findById(ID id);

    /**
     * Delete an entity by id
     * @param id the id of the entity to delete
     * @return the deleted entity
     */
    T delete(ID id);
}
