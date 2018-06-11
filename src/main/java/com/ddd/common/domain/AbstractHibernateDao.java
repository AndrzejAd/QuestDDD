package com.ddd.common.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public abstract class AbstractHibernateDao <T extends PersistentClass> {
    private Class< T > clazz;

    @PersistenceContext
    private EntityManager entityManager;

    public void setClazz(Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public Optional<T> findById(Long id ){
        return Optional.ofNullable(entityManager.find( clazz, id ));
    }
    public List< T > findAll(){
        return entityManager
                .createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public void save( T entity ){
        entityManager.persist( entity );
    }

    public void update( T entity ){
        entityManager.merge( entity );
    }

    public void delete( T entity ){
        entityManager.remove( entity );
    }

    public void deleteById( Long entityId ){
        findById( entityId ).ifPresent( this::delete);
    }

    public void flushAndClearForBatchProcessing(){
        entityManager.flush();
        entityManager.clear();
    }

}
