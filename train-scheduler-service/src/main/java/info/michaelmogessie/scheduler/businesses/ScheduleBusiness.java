package info.michaelmogessie.scheduler.businesses;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.michaelmogessie.handlers.SchedulesWebSocketHandler;
import info.michaelmogessie.scheduler.pojos.Schedule;
import info.michaelmogessie.scheduler.pojos.SimpleSchedule;
import info.michaelmogessie.scheduler.pojos.StationSchedule;
import info.michaelmogessie.scheduler.pojos.TrainScheduleHelper;
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
        schedule.getCheckPoints().forEach(checkPoint -> checkPoint
                .setStation(stationRepository.findById(checkPoint.getStation().getStationId()).get()));
        schedule.setTrain(trainRepository.findById(schedule.getTrain().getTrainId()).get());
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public StationSchedule getStationSchedule(int stationId) {
        return new StationSchedule(
                scheduleRepository.findByCheckPointsStationStationIdEqualsOrderByCheckPointsEtaAsc(stationId),
                stationId);
    }

    @Transactional
    public List<SimpleSchedule> getTrainSchedules(int trainId) {
        return TrainScheduleHelper.createTrainSchedules(scheduleRepository.findByTrainTrainId(trainId), null);
    }

    @Transactional
    public void updateTrainSchedule(SimpleSchedule trainSchedule) {
        Schedule schedule = scheduleRepository.findById(trainSchedule.getScheduleId()).get();
        schedule.setAtStation(trainSchedule.getAtStation());
        schedule.setStatus(trainSchedule.getStatus());
        scheduleRepository.save(schedule);
        schedule.getCheckPoints().forEach(checkPoint -> {
            SchedulesWebSocketHandler.sendStationUpdate(checkPoint.getStation().getStationId(), trainSchedule);
        });
    }

}
