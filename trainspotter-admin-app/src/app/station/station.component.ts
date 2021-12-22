import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TabchangeService } from '../services/tabchange.service';

const stationNames = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQR', 'STU', 'VWX', 'YZA'];

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.scss']
})

export class StationComponent implements OnInit {
  displayedColumns: string[] = ['stationId', 'stationName'];
  dataSource: any = [];
  tabIndex = 2;
  constructor(private http: HttpClient, private tabChangeService: TabchangeService) { }
  ngOnInit(): void {
    this.tabChangeService.tabChange.subscribe((tabIndex: number) => {
      if (this.tabIndex === tabIndex) {
        this.getStations();
      }
    });
  }

  getStations() {
    this.http.get('http://localhost:30033/stations').subscribe((stations: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.dataSource = stations['_embedded'].stationList;
    });
  }
  addStation() {
    const station = {};
    // eslint-disable-next-line @typescript-eslint/dot-notation
    station['stationName'] = stationNames[Math.round(Math.random() * 10)] + '_' + Math.floor((Math.random() * 10));
    this.http.post('http://localhost:30033/stations', station)
      .subscribe((savedStation: any) => {
        // eslint-disable-next-line @typescript-eslint/dot-notation
        this.dataSource.push(savedStation);
        this.dataSource = [...this.dataSource];
      });
  }

}
