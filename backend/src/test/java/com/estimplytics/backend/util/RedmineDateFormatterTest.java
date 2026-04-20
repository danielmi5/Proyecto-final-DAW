package com.estimplytics.backend.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RedmineDateFormatterTest {

    @Test
    void formatUpdatedOnFilter_shouldUseUtcRedmineFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2026, 5, 22, 13, 45, 30);

        String formatted = RedmineDateFormatter.formatUpdatedOnFilter(dateTime);

        assertThat(formatted).isEqualTo("2026-05-22T13:45:30Z");
    }
}
