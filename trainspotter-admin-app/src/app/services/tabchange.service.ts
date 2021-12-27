import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TabchangeService {
  @Output()
  tabChange: EventEmitter<number> = new EventEmitter<number>();
  @Output()
  trains: EventEmitter<any> = new EventEmitter<any>();
  @Output()
  stations: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  public emitTabChangeEvent(tabIndex: any) {
    this.tabChange.emit(tabIndex);
  }
  public emitTrainsEvent(trains: any) {
    this.trains.emit(trains);
  }
  public emitStationsEvent(stations: any) {
    this.stations.emit(stations);
  }
}
