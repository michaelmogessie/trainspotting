import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArrivalUpdateService {

  @Output()
  arrivalUpdate: EventEmitter<{}> = new EventEmitter<{}>();
  singleArrivalUpdate: EventEmitter<{}> = new EventEmitter<{}>();
  public emitArrivalUpdateEvent(arrivals: {}) {
    this.arrivalUpdate.emit(arrivals);
  }

  public emitSingleArrivalUpdateEvent(stationUpdate: {}) {
    this.singleArrivalUpdate.emit(stationUpdate);
  }
  constructor() { }
}
