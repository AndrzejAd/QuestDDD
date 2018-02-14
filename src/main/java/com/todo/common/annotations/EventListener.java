package com.todo.common.annotations;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Service
@Target(ElementType.TYPE)
public @interface EventListener {
}
