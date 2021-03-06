package models;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class WorkoutAddFactory {
    public static WorkoutQuickAdd get() {
        int currentDay = LocalDate.now().getDayOfMonth();
        DateFormat dateFormat2 = new SimpleDateFormat(String.format("MM/%s/yyyy", currentDay - 2));
        Date date2 = new Date();
        String workoutDay = dateFormat2.format(date2);
        Faker faker = new Faker();
        return WorkoutQuickAdd.builder()
                .date(workoutDay)
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
                .isSavedToLibrary(false)
                .build();
    }

    public static WorkoutFullAdd getFullAdd() {
        int currentDay = LocalDate.now().getDayOfMonth();
        DateFormat dateFormat2 = new SimpleDateFormat(String.format("MM/%s/yyyy", currentDay - 2));
        Date date2 = new Date();
        String workoutDay = dateFormat2.format(date2);
        Faker faker = new Faker();
        return WorkoutFullAdd.builder()
                .date(workoutDay)
                .time("11:00 AM")
                .workoutName(faker.name().name())
                .description(faker.lebowski().quote())
                .isPlanned(faker.random().nextBoolean())
                .plannedDistance(faker.random().nextInt(2, 15).toString())
                .plannedDuration("01:22:22")
                .distance(faker.random().nextInt(2, 15).toString())
                .duration("01:22:22")
                .howIFelt("Great")
                .perceivedEffort("1 (Very Light)")
                .elevationGain(faker.random().nextInt(2, 15).toString())
                .elevationLoss(faker.random().nextInt(2, 15).toString())
                .caloriesBurned(faker.random().nextInt(200, 500).toString())
                .build();
    }
}
//faker.random().nextBoolean()