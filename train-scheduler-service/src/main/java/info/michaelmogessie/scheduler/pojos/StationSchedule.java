package info.michaelmogessie.scheduler.pojos;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

public class StationSchedule {
    private Map<Integer, Map<Integer, Map<Timestamp, Train>>> departures = new HashMap<>();
    private Map<Integer, Map<Integer, Map<Timestamp, Train>>> arrivals = new HashMap<>();

    public StationSchedule(List<Schedule> schedules, int stationId) {
        for (Schedule schedule : schedules) {
            CheckPoint cp = schedule.getCheckPoints().stream()
                    .filter(checkPoint -> checkPoint.getStation().getStationId() == stationId).toList().get(0);

            arrivals.put(schedule.getScheduleId(), Collections.singletonMap(schedule.getTrain().getTrainId(),
                    Collections.singletonMap(cp.getEta(), schedule.getTrain())));
            departures.put(schedule.getScheduleId(), Collections.singletonMap(schedule.getTrain().getTrainId(),
                    Collections.singletonMap(
                            new Timestamp(
                                    new DateTime(cp.getEta()).plusMinutes(cp.getStopDurationMinutes()).getMillis()),
                            schedule.getTrain())));
        }
    }

    public Map<Integer, Map<Integer, Map<Timestamp, Train>>> getDepartures() {
        return departures;
    }

    public void setDepartures(Map<Integer, Map<Integer, Map<Timestamp, Train>>> departures) {
        this.departures = departures;
    }

    public Map<Integer, Map<Integer, Map<Timestamp, Train>>> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Map<Integer, Map<Integer, Map<Timestamp, Train>>> arrivals) {
        this.arrivals = arrivals;
    }

}
