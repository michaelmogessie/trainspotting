package info.michaelmogessie.scheduler.businesses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import info.michaelmogessie.scheduler.pojos.Schedule;
import info.michaelmogessie.scheduler.pojos.Station;
import info.michaelmogessie.scheduler.pojos.Train;
import info.michaelmogessie.scheduler.repositories.ScheduleRepository;
import info.michaelmogessie.scheduler.repositories.StationRepository;
import info.michaelmogessie.scheduler.repositories.TrainRepository;

@Service
public class ScheduleBusiness {
    private final ScheduleRepository scheduleRepository;
    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;

    public ScheduleBusiness(ScheduleRepository scheduleRepository, TrainRepository trainRepository,
            StationRepository stationRepository) {
        this.scheduleRepository = scheduleRepository;
        this.trainRepository = trainRepository;
        this.stationRepository = stationRepository;
    }

    public Schedule saveSchedule(Schedule schedule) {
        Map<Integer, Station> stationMap = schedule.getStations().stream()
                .collect(Collectors.toMap(Station::getStationId, Function.identity()));
        List<Station> stations = new ArrayList<>();
        stationMap.entrySet().stream()
                .map(station -> Map.of(stationRepository.findById(station.getKey()), station.getValue()))
                .forEach(station -> {
                    for (Map.Entry<Optional<Station>, Station> s : station.entrySet()) {
                        if (s.getKey().isPresent()) {
                            stations.add((Station) s.getKey().get());
                        } else {
                            stations.add((Station) s.getValue());
                        }
                        break;
                    }
                });
        schedule.setStations(stations);

        Optional<Train> train = trainRepository.findById(schedule.getTrain().getTrainId());
        if (train.isPresent()) {
            schedule.setTrain(train.get());
        }
        return scheduleRepository.save(schedule);
    }
}
