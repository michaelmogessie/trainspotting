import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { webSocket } from 'rxjs/webSocket';
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
  webSocket: any

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
    if (this.webSocket) {
      this.webSocket.complete();
    }
    this.webSocket = webSocket('ws://localhost:8485/station-schedules?stationId=' + stationId);
    this.webSocket.asObservable().subscribe((stationUpdate: any) => {
      if (stationUpdate && stationUpdate.departures) this.departureUpdateService.emitDepartureUpdateEvent(stationUpdate.departures);
      if (stationUpdate && stationUpdate.arrivals) this.arrivalUpdateService.emitArrivalUpdateEvent(stationUpdate.arrivals);
      if (stationUpdate.scheduleId) {
        this.departureUpdateService.emitSingleDepartureUpdateEvent(stationUpdate);
        this.arrivalUpdateService.emitSingleArrivalUpdateEvent(stationUpdate);
      }
    })


  }

  stationChanged(stationId: number) {
    this.getSchedules(stationId);
  }

}
