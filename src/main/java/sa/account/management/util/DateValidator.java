package sa.account.management.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    private DateValidator() {}

    public static boolean isValidDate(String dateStr, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
