package info.michaelmogessie.scheduler.pojos;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.hateoas.RepresentationModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Schedule extends RepresentationModel<Schedule> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    @OneToOne(cascade = { CascadeType.ALL })
    private Train train;
    @ManyToMany(cascade = { CascadeType.ALL })
    List<CheckPoint> checkPoints = new LinkedList<>();
    @Column(columnDefinition = "varchar(12) not null default 'ON TIME'")
    private String status = statusOnTime;

    public static final String statusOnTime = "ON TIME";
    public static final String statusDelayed = "DELAYED";
    public static final String statusCanceled = "CANCELED";

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CheckPoint> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(List<CheckPoint> checkPoints) {
        this.checkPoints = checkPoints;
    }

}
