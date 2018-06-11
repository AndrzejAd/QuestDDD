package com.ddd.common.domain;

import java.util.List;
import java.util.Optional;

public interface IGenericCrudDao<T extends PersistentClass> {

    Optional<T> findById(final Long id);

    List<T> findAll();

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);

    void setClazz(Class<T> clazzToSet);

    void flushAndClearForBatchProcessing();

}
