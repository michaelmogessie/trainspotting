package info.michaelmogessie.scheduler.pojos;

import java.sql.Date;

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
    private int checkpointId;
    @OneToOne(cascade = { CascadeType.ALL })
    private Station station;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date eta;
    @Column(nullable = false, columnDefinition = "int default 5")
    private int stopDurationMinutes;

    public int getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public int getStopDurationMinutes() {
        return stopDurationMinutes;
    }

    public void setStopDurationMinutes(int stopDurationMinutes) {
        this.stopDurationMinutes = stopDurationMinutes;
    }

}
