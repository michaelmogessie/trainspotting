import { Component, OnInit } from '@angular/core';
import { DepartureUpdateService } from '../services/departure-update.service';

@Component({
  selector: 'app-departures',
  templateUrl: './departures.component.html',
  styleUrls: ['./departures.component.scss']
})
export class DeparturesComponent implements OnInit {
  departures: any;
  constructor(private departureUpdateService: DepartureUpdateService) { }

  ngOnInit(): void {
    this.departureUpdateService.departureUpdate.subscribe((departures: {}) => {
      this.departures = departures;
    });
    this.departureUpdateService.singleDepartureUpdate.subscribe((stationUpdate: any) => {
      this.departures.filter(this.findDeparturesToUpdate(stationUpdate)).map((departure: any) => {
        departure.atStation = stationUpdate.atStation
        departure.status = stationUpdate.status
      })
    })
  }

  findDeparturesToUpdate(stationUpdate: any) {
    return function (departure: any) {
      return departure.scheduleId == stationUpdate.scheduleId && departure.trainId == stationUpdate.trainId
    }
  }

}