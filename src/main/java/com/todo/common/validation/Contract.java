package com.todo.common.validation;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

public class Contract {

    public static void notNull(Object object){
        isTrue(Objects.nonNull(object), NonNullExpected::new);
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

    public static <T extends ContractBroken> void isTrue(boolean condition, Supplier<T> exceptionSupplier){
        if(!condition){
            throw exceptionSupplier.get();
        }
    }

}
