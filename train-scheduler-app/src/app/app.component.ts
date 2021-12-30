import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'train-scheduler-app';
  schedules = [];
  trains = [];
  stations = [];

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.getSchedules();
    this.getTrains();
    this.getStations();
  }


  getSchedules() {
    console.table(this.http)
    this.http.get('http://localhost:8485/schedules').subscribe((schedules: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      if (schedules['_embedded']) {
        // eslint-disable-next-line @typescript-eslint/dot-notation
        this.schedules = schedules['_embedded'].scheduleList;
      }
    });
  };

  getTrains() {
    this.http.get('http://localhost:8486/trains').subscribe((trains: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.trains = trains['_embedded'].trainList;
    });
  }

  getStations() {
    this.http.get('http://localhost:8487/stations').subscribe((stations: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.stations = stations['_embedded'].stationList;
    });
  }

  addSchedule() {
    const schedule = {};
    schedule['departureTime'] = Date.now();
    schedule['arrivalTime'] = Date.now();
    schedule['train'] = this.trains[0];
    schedule['stations'] = this.stations;
    this.http.post('http://localhost:30031/schedules', schedule).subscribe((savedSchedule: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.schedules.push(savedSchedule);
    });
  }

  removeCheckPoint(checkPoint) {

  }

  addCheckPoint($event) {

  }
}

