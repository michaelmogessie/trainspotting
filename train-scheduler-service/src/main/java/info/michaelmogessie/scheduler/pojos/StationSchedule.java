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

            arrivals.add(new SimpleSchedule(schedule.getScheduleId(), schedule.getTrain().getTrainId(),
                    schedule.getTrain().getTrainName(), cp.getEta()));
            departures.add(new SimpleSchedule(schedule.getScheduleId(), schedule.getTrain().getTrainId(),
                    schedule.getTrain().getTrainName(), new Timestamp(
                            new DateTime(cp.getEta()).plusMinutes(cp.getStopDurationMinutes()).getMillis())));

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
