package info.michaelmogessie.scheduler.pojos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TrainScheduleHelper {
    private TrainScheduleHelper() {

    }

    public static List<SimpleSchedule> createTrainSchedules(List<Schedule> schedules, Station atStation) {
        List<SimpleSchedule> trainSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            List<Station> stations = schedule.getCheckPoints().stream().map(checkPoint -> checkPoint.getStation())
                    .toList();
            Timestamp eta = !schedule.getCheckPoints().isEmpty()
                    ? schedule.getCheckPoints().get(schedule.getCheckPoints().size() - 1).getEta()
                    : null;
            Station fromStation = !schedule.getCheckPoints().isEmpty() ? schedule.getCheckPoints().get(0).getStation()
                    : null;
            Station toStation = schedule.getCheckPoints().size() > 1
                    ? schedule.getCheckPoints().get(schedule.getCheckPoints().size() - 1).getStation()
                    : null;
            trainSchedules.add(new SimpleSchedule(schedule.getScheduleId(),
                    schedule.getTrain().getTrainId(), schedule.getTrain().getTrainName(), eta,
                    fromStation.getStationName(), toStation.getStationName(),
                    atStation, schedule.getStatus(), stations));
        }
        return trainSchedules;
    }
}
