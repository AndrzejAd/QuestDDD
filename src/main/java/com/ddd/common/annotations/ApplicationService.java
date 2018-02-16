package com.ddd.common.annotations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation = Propagation.REQUIRED)
public @interface ApplicationService {
}
