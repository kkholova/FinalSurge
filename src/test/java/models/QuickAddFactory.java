package models;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuickAddFactory {
    public static WorkoutQuickAdd get() {
//TODO преобразовать дату в американский формат и добавить рандом?
        Faker faker = new Faker();
        return WorkoutQuickAdd.builder()
                .date("6/26/2021")
                .time("11:00 AM")
                .activityType("Run")
                .workoutName(faker.name().name())
                .description(faker.lebowski().quote())
                .isPlanned(faker.random().nextBoolean())
                .plannedDistance(faker.random().nextInt(2, 15).toString())
                .plannedDuration("01:22:22")
                .distance(faker.random().nextInt(2, 15).toString())
                .duration("01:22:22")
                .howIFelt("Great")
                .perceivedEffort("1 (Very Light)")
                .isSavedToLibrary(faker.random().nextBoolean())
                .build();
    }

}
