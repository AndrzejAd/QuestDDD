package com.ddd.common.domain;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericCrudHibernateRepository<T extends PersistentClass> extends AbstractHibernateDao<T> implements IGenericCrudDao<T> {

}
