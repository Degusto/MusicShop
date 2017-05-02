package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date utilities
 */
public final class DateUtilities {
    /**
     * Parses string to date using yyyy-MM-dd format
     * @param date string to parse
     * @return parsed date
     * @throws ParseException thrown when string is not in correct format
     */
    public static Date parse(final String date) throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.parse(date);
    }

    /**
     * Formats date to string using yyyy-MM-dd format
     * @param date date to format
     * @return formatted string
     */
    public static String format(final Date date){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(date);
    }
}
