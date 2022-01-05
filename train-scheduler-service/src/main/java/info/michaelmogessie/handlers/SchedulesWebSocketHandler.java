package info.michaelmogessie.handlers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SchedulesWebSocketHandler extends TextWebSocketHandler {
    public static List<WebSocketSession> stationKiosks = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession stationKiosk, TextMessage message) throws Exception {
        stationKiosk.sendMessage(message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        stationKiosks.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession disconnectedStationKiosk, CloseStatus status) throws Exception {
        stationKiosks = new CopyOnWriteArrayList<>(stationKiosks.stream()
                .filter(stationKiosk -> stationKiosk.getId().equals(disconnectedStationKiosk.getId())).toList());
    }

    public void sendMessageToAllStationKiosks(TextMessage message) throws Exception {
        stationKiosks.forEach(stationKiosk -> {
            try {
                stationKiosk.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
