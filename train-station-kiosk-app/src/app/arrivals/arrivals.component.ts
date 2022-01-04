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
  }

}
