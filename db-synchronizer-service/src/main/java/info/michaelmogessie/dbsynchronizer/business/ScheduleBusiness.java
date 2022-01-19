package info.michaelmogessie.dbsynchronizer.business;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.michaelmogessie.dbsynchronizer.pojos.CheckPoint;
import info.michaelmogessie.dbsynchronizer.pojos.Schedule;
import info.michaelmogessie.dbsynchronizer.pojos.Station;
import info.michaelmogessie.dbsynchronizer.pojos.Train;
import info.michaelmogessie.dbsynchronizer.repositories.ScheduleRepository;
import info.michaelmogessie.dbsynchronizer.repositories.StationRepository;
import info.michaelmogessie.dbsynchronizer.repositories.TrainRepository;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
public class ScheduleBusiness {
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private StationRepository stationRepository;

    public ScheduleBusiness(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void updateTrains(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        for (Message message : messages) {
            updateTrain(message);
        }
    }

    private void updateTrain(Message message) throws JsonProcessingException, JsonMappingException {
        try {
            scheduleRepository.deleteByTrainTrainIdEquals(Integer.valueOf(message.body()));

        } catch (Exception e) {
            trainRepository.save(new ObjectMapper().readValue(message.body(), Train.class));
        }
    }

    @Transactional
    public void updateStations(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        for (Message message : messages) {
            updateStation(message);
        }
    }

    private void updateStation(Message message) throws JsonProcessingException, JsonMappingException {
        try {
            Integer stationId = Integer.valueOf(message.body());
            List<Schedule> schedules = scheduleRepository.findByCheckPointsStationStationIdEquals(stationId);

            //This throws and exception.
            // schedules.forEach(schedule -> schedule.setCheckPoints(schedule.getCheckPoints().stream()
            //         .filter(checkPoint -> checkPoint.getStation().getStationId() != stationId).toList()));
            for(Schedule schedule : schedules) {
                List<CheckPoint> checkPoints = new LinkedList<>();
                for(CheckPoint cp : schedule.getCheckPoints()) {
                    if(cp.getStation().getStationId() != stationId) {
                        checkPoints.add(cp);
                    }
                }
                schedule.setCheckPoints(checkPoints);
            }
            scheduleRepository.saveAll(schedules);
            stationRepository.deleteById(stationId);
        } catch (Exception e) {
            stationRepository.save(new ObjectMapper().readValue(message.body(), Station.class));
        }
    }

}
