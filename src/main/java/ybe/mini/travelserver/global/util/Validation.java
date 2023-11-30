package ybe.mini.travelserver.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ybe.mini.travelserver.global.exception.dto.DateFormatNotCurrentOrFutureException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validation {

    public static final String DATE_PATTERN = "yyyyMMdd";

    public static LocalDate validateDateFormat(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate nowDate = LocalDate.now();
        if (!date.isBefore(nowDate)) return date;
        else throw new DateFormatNotCurrentOrFutureException();
    }
}
