package info.michaelmogessie.dbsynchronizer.business;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import info.michaelmogessie.dbsynchronizer.pojos.Schedule;
import info.michaelmogessie.dbsynchronizer.pojos.Train;
import info.michaelmogessie.dbsynchronizer.repositories.ScheduleRepository;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
public class ScheduleBusiness {
    private final ScheduleRepository scheduleRepository;

    public ScheduleBusiness(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findSchedulesByTrainId(Integer trainId) {
        return scheduleRepository.findByTrainTrainIdEquals(trainId);
    }

    public void updateTrains(List<Message> messages) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        for (Message message : messages) {
            Train train = mapper.readValue(message.body(), Train.class);
            List<Schedule> schedules = findSchedulesByTrainId(train.getTrainId());
            for (Schedule schedule : schedules) {
                schedule.setTrain(train);
                scheduleRepository.save(schedule);
            }
        }
    }
}
