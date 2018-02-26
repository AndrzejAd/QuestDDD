package com.ddd.common.validation;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

public class Contract {

    public static void notNull(Object object){
        isTrue(Objects.nonNull(object), NonNullExpected::new);
    }

    public static void notNull(Object... objects){
        for ( Object o : objects) notNull(o);
    }

    public static void notEmptyCollection(Collection collection){
        isTrue( collection.isEmpty(), NotEmptyCollectionExpected::new );
    }

    public static void matches(String word, String regex){
        notNull(word);
        isTrue(word.matches(regex), RegexMatchFailed::new);
    }

    public static void matchesSize(String word, int min, int max){
        notNull(word);
        isTrue(word.length() >= min && word.length() <= max, OtherSizeExpected::new);
    }

    public static <T extends Number> void isBetween( T value, T max, T min){
        notNull(value, max, min);
        isTrue( value.doubleValue() < max.doubleValue() && value.doubleValue() > min.doubleValue(), WrongValue::new );
    }

    public static <T extends Number, E extends ContractBroken> void isBetween( T value, T max, T min, Supplier<E> exceptionSupplier){
        notNull(value, max, min);
        isTrue( value.doubleValue() < max.doubleValue() && value.doubleValue() > min.doubleValue(), exceptionSupplier::get );
    }

    public static <T extends ContractBroken> void isTrue(boolean condition, Supplier<T> exceptionSupplier){
        if(!condition){
            throw exceptionSupplier.get();
        }
    }



}
