package utilities;

/**
 * Double utilities
 */
public final class DoubleUtilities {
    /**
     * Tries parse string to Double
     * @param number string number
     * @return parsed number or null if string is not correct double value
     */
    public static Double tryParse(final String number) {
        try {
            return Double.parseDouble(number);
        } catch (final NumberFormatException nfe) {
            return null;
        }
    }
}
