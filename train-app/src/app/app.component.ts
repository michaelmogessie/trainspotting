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
        console.table(this.schedules)
      }
    });
  }

  trainChanged(trainId: number) {
    this.getTrainSchedules(trainId);
  }

  stationChanged(scheduleId: number, station: any) {

  }

  statusChanged(scheduleId: number, status: string) {

  }
}
