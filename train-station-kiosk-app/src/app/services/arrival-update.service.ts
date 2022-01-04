import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArrivalUpdateService {

  @Output()
  arrivalUpdate: EventEmitter<{}> = new EventEmitter<{}>();

  public emitArrivalUpdateEvent(arrivals: {}) {
    this.arrivalUpdate.emit(arrivals);
  }
  constructor() { }
}
