package utilities;

/**
 * Integer utilities
 */
public final class IntegerUtilities {
    /**
     * Tries parse string to Integer
     * @param number string to parse
     * @return parsed number or null if string is not correct Integer value
     */
    public static Integer tryParse(final String number){
        try {
            return Integer.parseInt(number);
        } catch (final NumberFormatException nfe) {
            return null;
        }
    }
}
