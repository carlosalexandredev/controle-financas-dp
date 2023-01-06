package com.example.demo.fortune.util;

import java.time.format.DateTimeFormatter;

public abstract class DateUtil {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateUtil() {
    }
}
