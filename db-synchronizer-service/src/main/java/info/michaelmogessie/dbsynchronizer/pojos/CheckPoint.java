package info.michaelmogessie.dbsynchronizer.pojos;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CheckPoint {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int checkPointId;
    @OneToOne(cascade = { CascadeType.PERSIST })
    private Station station;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp eta;
    @Column(nullable = false, columnDefinition = "int default 5")
    private int stopDurationMinutes;

    public int getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(int checkPointId) {
        this.checkPointId = checkPointId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getStopDurationMinutes() {
        return stopDurationMinutes;
    }

    public void setStopDurationMinutes(int stopDurationMinutes) {
        this.stopDurationMinutes = stopDurationMinutes;
    }

    public Timestamp getEta() {
        return eta;
    }

    public void setEta(Timestamp eta) {
        this.eta = eta;
    }

}
