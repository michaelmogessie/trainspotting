package info.michaelmogessie.scheduler.pojos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class StationSchedule {
    private List<SimpleSchedule> departures = new ArrayList<>();
    private List<SimpleSchedule> arrivals = new ArrayList<>();

    public StationSchedule(List<Schedule> schedules, int stationId) {
        for (Schedule schedule : schedules) {
            CheckPoint cp = schedule.getCheckPoints().stream()
                    .filter(checkPoint -> checkPoint.getStation().getStationId() == stationId).toList().get(0);
            if (schedule.getCheckPoints().get(0).getStation().getStationId() != stationId) {
                arrivals.add(new SimpleSchedule(
                        schedule.getScheduleId(),
                        schedule.getTrain().getTrainId(),
                        schedule.getTrain().getTrainName(),
                        cp.getEta(),
                        !schedule.getCheckPoints().isEmpty()
                                ? schedule.getCheckPoints().get(0).getStation().getStationName()
                                : null,
                        null, schedule.getStatus()));
            }
            if (schedule.getCheckPoints()
                    .get(schedule.getCheckPoints().size() - 1).getStation().getStationId() != stationId
                    && !schedule.getCheckPoints().isEmpty()) {
                departures.add(new SimpleSchedule(
                        schedule.getScheduleId(),
                        schedule.getTrain().getTrainId(),
                        schedule.getTrain().getTrainName(),
                        new Timestamp(new DateTime(cp.getEta()).plusMinutes(cp.getStopDurationMinutes()).getMillis()),
                        null, schedule.getCheckPoints()
                                .get(schedule.getCheckPoints().size() - 1).getStation().getStationName(),
                        schedule.getStatus()));
            }

        }
    }

    public List<SimpleSchedule> getDepartures() {
        return departures;
    }

    public void setDepartures(List<SimpleSchedule> departures) {
        this.departures = departures;
    }

    public List<SimpleSchedule> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<SimpleSchedule> arrivals) {
        this.arrivals = arrivals;
    }

}
