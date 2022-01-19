import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'train-app';
  trainId: any
  trains: any
  schedules: any
  schedule: any
  atStation: any
  status: any

  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
    this.getTrains();
  }

  getTrains() {
    this.http.get('http://localhost:8486/trains').subscribe((trains: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      let trainList = trains['_embedded'];
      if (trainList) {
        this.trains = trainList.trainList;
      }
    });
  }

  getTrainSchedules(trainId: number) {
    this.http.get('http://localhost:8485/train-schedules/' + trainId).subscribe((schedules: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      let scheduleList = schedules['_embedded'];
      if (scheduleList) {
        this.schedules = scheduleList.simpleScheduleList;
      }
    });
  }

  trainChanged(trainId: number) {
    this.getTrainSchedules(trainId);
  }

  stationChanged(scheduleId: number, station: any) {
    let schedule = this.schedules.filter((schedule: any) => schedule.scheduleId === scheduleId)[0];
    schedule.atStation = station;
    this.http.post('http://localhost:8485/train-schedules/', schedule).subscribe((schedule: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
    });
  }

  statusChanged(scheduleId: number, status: string) {
    let schedule = this.schedules.filter((schedule: any) => schedule.scheduleId === scheduleId)[0];
    schedule.status = status;
    this.http.post('http://localhost:8485/train-schedules/', schedule).subscribe((schedule: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
    });

  }
}
