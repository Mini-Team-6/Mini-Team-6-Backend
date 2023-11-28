package ybe.mini.travelserver.global.util;

import ybe.mini.travelserver.global.exception.dto.DateFormatNotCurrentOrFutureException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {

    public static final String datePattern = "yyyyMMdd";

    public static LocalDate validateDateFormat(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate nowDate = LocalDate.now();
        if(date.compareTo(nowDate) >= 0) return date;
        else throw new DateFormatNotCurrentOrFutureException();
    }
}
