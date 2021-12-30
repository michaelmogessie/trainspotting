import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TabchangeService {

  @Output()
  tabChange: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  public emitTabChangeEvent(tabIndex: any) {
    this.tabChange.emit(tabIndex);
  }
}
