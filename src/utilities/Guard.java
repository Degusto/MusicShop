package utilities;

/**
 * Guard utility class
 */
public final class Guard {
    /**
     * Throws IllegalArgumentException if argument is null
     * @param object object to check
     * @param field field name in exception message
     * @param <T> object type
     */
    public static <T> void notNull(final T object, final String field) {
        if (object == null) {
            throw new IllegalArgumentException(field + " can't be a null");
        }
    }
}
