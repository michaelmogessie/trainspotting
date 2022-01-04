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
  }

}