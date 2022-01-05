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
    // this.http.get('http://localhost:8485/station-schedules/' + stationId).subscribe((schedules: any) => {
    //   // eslint-disable-next-line @typescript-eslint/dot-notation
    //   if (schedules) {
    //     this.departureUpdateService.emitDepartureUpdateEvent(schedules.departures);
    //     this.arrivalUpdateService.emitArrivalUpdateEvent(schedules.arrivals);
    //   }
    // });

    if (this.webSocket) {
      this.webSocket.complete();
    }
    this.webSocket = webSocket('ws://localhost:8485/station-schedules/' + stationId);
    this.webSocket.asObservable().subscribe((response: any) => {
      let stationUpdate = JSON.parse(response.data);
      console.table(stationUpdate)
      this.departureUpdateService.emitDepartureUpdateEvent(stationUpdate.departures);
      this.arrivalUpdateService.emitArrivalUpdateEvent(stationUpdate.arrivals);
    })


  }

  stationChanged(stationId: number) {
    this.getSchedules(stationId);
  }

}
