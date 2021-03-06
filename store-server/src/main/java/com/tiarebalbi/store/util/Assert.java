package com.tiarebalbi.store.util;

/**
 * @author Tiarê Balbi Bonamini
 * @version 1.0.0
 */
public final class Assert {

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        if(object == null) {
            throw new IllegalArgumentException("This argument is required; it must not be null");
        }
    }

    public static void isTrue(boolean expression, String message) {
        if(!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "This expression must be true");
    }
}
