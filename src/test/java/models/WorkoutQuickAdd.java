package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class WorkoutQuickAdd {
    String date;
    String time;
    String activityType;
    String workoutName;
    String description;
    boolean isPlanned;
    String plannedDistance;
    String plannedDuration;
    String distance;
    String duration;
    String howIFelt;
    String perceivedEffort;
    boolean isSavedToLibrary;



}
