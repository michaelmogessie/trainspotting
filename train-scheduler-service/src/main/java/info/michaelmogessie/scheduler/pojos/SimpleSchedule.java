package info.michaelmogessie.scheduler.pojos;

import java.sql.Timestamp;
import java.util.List;

public class SimpleSchedule {
    private int scheduleId;
    private int trainId;
    private String trainName;
    private Timestamp eta;
    private String fromStation;
    private String toStation;
    private Station atStation;
    private String status;
    private List<Station> stations;
    private List<String> statuses = List.of(Schedule.statusOnTime, Schedule.statusDelayed, Schedule.statusCanceled);

    public int getScheduleId() {
        return scheduleId;
    }

    public Station getAtStation() {
        return atStation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((atStation == null) ? 0 : atStation.hashCode());
        result = prime * result + ((eta == null) ? 0 : eta.hashCode());
        result = prime * result + ((fromStation == null) ? 0 : fromStation.hashCode());
        result = prime * result + scheduleId;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((toStation == null) ? 0 : toStation.hashCode());
        result = prime * result + trainId;
        result = prime * result + ((trainName == null) ? 0 : trainName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleSchedule other = (SimpleSchedule) obj;
        if (atStation == null) {
            if (other.atStation != null)
                return false;
        } else if (!atStation.equals(other.atStation))
            return false;
        if (eta == null) {
            if (other.eta != null)
                return false;
        } else if (!eta.equals(other.eta))
            return false;
        if (fromStation == null) {
            if (other.fromStation != null)
                return false;
        } else if (!fromStation.equals(other.fromStation))
            return false;
        if (scheduleId != other.scheduleId)
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (toStation == null) {
            if (other.toStation != null)
                return false;
        } else if (!toStation.equals(other.toStation))
            return false;
        if (trainId != other.trainId)
            return false;
        if (trainName == null) {
            if (other.trainName != null)
                return false;
        } else if (!trainName.equals(other.trainName))
            return false;
        return true;
    }

    public void setAtStation(Station atStation) {
        this.atStation = atStation;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Timestamp getEta() {
        return eta;
    }

    public void setEta(Timestamp eta) {
        this.eta = eta;
    }

    public SimpleSchedule() {

    }

    public SimpleSchedule(int scheduleId, int trainId, String trainName, Timestamp eta, String fromStation,
            String toStation, String status) {
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.trainName = trainName;
        this.eta = eta;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.status = status;
    }

    public SimpleSchedule(int scheduleId, int trainId, String trainName, Timestamp eta, String fromStation,
            String toStation, Station atStation, String status, List<Station> stations) {
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.trainName = trainName;
        this.eta = eta;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.atStation = atStation;
        this.status = status;
        this.stations = stations;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }

}
