package info.michaelmogessie.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import info.michaelmogessie.scheduler.businesses.ScheduleBusiness;
import info.michaelmogessie.scheduler.pojos.SimpleSchedule;
import info.michaelmogessie.scheduler.pojos.StationSchedule;

@Component
public class SchedulesWebSocketHandler extends TextWebSocketHandler {
    private static Map<Integer, List<WebSocketSession>> stationKiosks = new HashMap<>();
    private static final String STATION_ID = "stationid";
    @Autowired
    private ScheduleBusiness scheduleBusiness;

    @Override
    public void handleTextMessage(WebSocketSession stationKiosk, TextMessage message) throws Exception {
        stationKiosk.sendMessage(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            String[] query = session.getUri().getQuery().split("=");
            int stationId = Integer.valueOf(query[1]);
            if (query[0].equalsIgnoreCase(STATION_ID)) {
                if (!stationKiosks.containsKey(stationId)) {
                    stationKiosks.put(stationId, new ArrayList<>());
                }
                stationKiosks.get(stationId).add(session);
                sendStationUpdate(stationId, scheduleBusiness.getStationSchedule(stationId));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.close(CloseStatus.BAD_DATA);
        }
    }

    public void sendStationUpdate(int stationId, StationSchedule stationSchedule) {
        stationKiosks.get(stationId).forEach(stationKiosk -> {
            try {
                stationKiosk.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(stationSchedule)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendStationUpdate(int stationId, SimpleSchedule simpleSchedule) {
        if (!stationKiosks.containsKey(stationId)) {
            return;
        }
        stationKiosks.get(stationId).forEach(stationKiosk -> {
            try {
                stationKiosk.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(simpleSchedule)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        stationKiosks.get(Integer.valueOf(session.getUri().getQuery().split("=")[1])).remove(session);
    }

}
