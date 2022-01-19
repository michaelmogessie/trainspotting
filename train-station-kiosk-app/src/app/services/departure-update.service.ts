import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DepartureUpdateService {
  @Output()
  departureUpdate: EventEmitter<{}> = new EventEmitter<{}>();
  singleDepartureUpdate: EventEmitter<{}> = new EventEmitter<{}>();

  public emitDepartureUpdateEvent(departures: {}) {
    this.departureUpdate.emit(departures);
  }
  public emitSingleDepartureUpdateEvent(stationUpdate: {}) {
    this.singleDepartureUpdate.emit(stationUpdate);
  }
  constructor() { }
}
