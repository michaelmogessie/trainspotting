import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ArrivalUpdateService } from './services/arrival-update.service';
import { DepartureUpdateService } from './services/departure-update.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'train-station-kiosk-app';
  stationId: any;
  stations: any;
  constructor(private http: HttpClient, private departureUpdateService: DepartureUpdateService, private arrivalUpdateService: ArrivalUpdateService) { }


  ngOnInit(): void {
    this.getStations();
  }

  getStations() {
    this.http.get('http://localhost:8487/stations').subscribe((stations: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      let stationList = stations['_embedded'];
      if (stationList) {
        this.stations = stationList.stationList;
      }
    });
  }


  getSchedules(stationId: number) {
    this.http.get('http://localhost:8485/station-schedules/' + stationId).subscribe((schedules: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      let scheduleList = schedules['_embedded'];
      if (scheduleList) {
        this.departureUpdateService.emitDepartureUpdateEvent(scheduleList.scheduleList.departures);
        this.arrivalUpdateService.emitArrivalUpdateEvent(scheduleList.scheduleList.arrivals);
      }
    });
  }

  stationChanged(stationId: number) {
    this.getSchedules(stationId);
  }

}
