package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void updateTrains(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        for (Message message : messages) {
            trainRepository.save(new ObjectMapper().readValue(message.body(), Train.class));
        }
    }

    @Transactional
    public void updateStations(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        for (Message message : messages) {
            stationRepository.save(new ObjectMapper().readValue(message.body(), Station.class));
        }
    }
}
