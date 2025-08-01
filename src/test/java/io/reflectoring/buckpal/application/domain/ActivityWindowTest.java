package io.reflectoring.buckpal.application.domain;

import io.reflectoring.buckpal.application.domain.model.ActivityWindow;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.reflectoring.buckpal.common.ActivityTestData.defaultActivity;

public class ActivityWindowTest {

    @Test
    void calculateStartTimestamp() {
        ActivityWindow window = new ActivityWindow(
                defaultActivity().withTimestamp(startDate()).build(),
                defaultActivity().withTimestamp(inBetweenDate()).build(),
                defaultActivity().withTimestamp(endDate()).build());

        Assertions.assertThat(window.getStartTimeStamp()).isEqualTo(startDate());

    }

    @Test
    void calculateEndTimestamp() {
        ActivityWindow window = new ActivityWindow(
                defaultActivity().withTimestamp(startDate()).build(),
                defaultActivity().withTimestamp(inBetweenDate()).build(),
                defaultActivity().withTimestamp(endDate()).build());

        Assertions.assertThat(window.getEndTimeStamp()).isEqualTo(endDate());
    }


    private LocalDateTime startDate() {
        return LocalDateTime.of(2019, 8, 3, 0, 0);

    }

    private LocalDateTime inBetweenDate() {
        return LocalDateTime.of(2019, 8, 4, 0, 0);
    }

    private LocalDateTime endDate() {
        return LocalDateTime.of(2019, 8, 5, 0, 0);
    }
}
