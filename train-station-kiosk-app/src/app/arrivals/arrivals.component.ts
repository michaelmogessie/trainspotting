import { Component, OnInit } from '@angular/core';
import { ArrivalUpdateService } from '../services/arrival-update.service';

@Component({
  selector: 'app-arrivals',
  templateUrl: './arrivals.component.html',
  styleUrls: ['./arrivals.component.scss']
})
export class ArrivalsComponent implements OnInit {
  arrivals: any;
  constructor(private arrivalUpdateService: ArrivalUpdateService) { }

  ngOnInit(): void {
    this.arrivalUpdateService.arrivalUpdate.subscribe((arrivals: {}) => {
      this.arrivals = arrivals;
    });
    this.arrivalUpdateService.singleArrivalUpdate.subscribe((stationUpdate: any) => {
      this.arrivals.filter(this.findArrivalsToUpdate(stationUpdate)).map((arrival: any) => {
        arrival.atStation = stationUpdate.atStation
        arrival.status = stationUpdate.status
      })
    })
  }

  findArrivalsToUpdate(stationUpdate: any) {
    return function (arrival: any) {
      return arrival.scheduleId == stationUpdate.scheduleId && arrival.trainId == stationUpdate.trainId
    }
  }

}
