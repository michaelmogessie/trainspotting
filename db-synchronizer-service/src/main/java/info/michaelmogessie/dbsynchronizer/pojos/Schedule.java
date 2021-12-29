package info.michaelmogessie.dbsynchronizer.pojos;

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
    List<CheckPoint> departures = new LinkedList<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    List<CheckPoint> arrivals = new LinkedList<>();

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

    public List<CheckPoint> getDepartures() {
        return departures;
    }

    public void setDepartures(List<CheckPoint> departures) {
        this.departures = departures;
    }

    public List<CheckPoint> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<CheckPoint> arrivals) {
        this.arrivals = arrivals;
    }

}
