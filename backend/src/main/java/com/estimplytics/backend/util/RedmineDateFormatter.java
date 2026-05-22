package com.estimplytics.backend.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class RedmineDateFormatter {

    private static final DateTimeFormatter UPDATED_ON_FILTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC);

    private RedmineDateFormatter() {
    }

    public static String formatUpdatedOnFilter(LocalDateTime dateTime) {
        return UPDATED_ON_FILTER.format(dateTime.atOffset(ZoneOffset.UTC));
    }
}
