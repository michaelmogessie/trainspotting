import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddCheckpointDialogComponent } from './add-checkpoint-dialog/add-checkpoint-dialog.component';
import { DeleteScheduleConfirmDialogComponent } from './delete-schedule-confirm-dialog/delete-schedule-confirm-dialog.component';

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
  statuses = ['ON TIME', 'DELAYED', 'CANCELED']

  constructor(private http: HttpClient, public dialog: MatDialog) { }
  ngOnInit(): void {
    this.getSchedules();
    this.getTrains();
    this.getStations();
  }


  getSchedules() {
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

  removeCheckPoint(scheduleId, checkPointId) {
    let schedule = this.schedules.filter(schedule => schedule.scheduleId == scheduleId)[0]
    let index = 0
    schedule.checkPoints.forEach(checkPoint => {
      if (checkPoint.checkPointId == checkPointId) return false;
      index += 1
    });
    schedule.checkPoints.splice(index, 1)
  }

  addCheckPoint(scheduleId) {
    let hours = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09']
    let minutes = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09']
    for (let i = 10; i < 24; i++) {
      hours.push(i + '')
    }
    for (let i = 10; i < 60; i++) {
      minutes.push(i + '')
    }

    const dialogRef = this.dialog.open(AddCheckpointDialogComponent, {
      width: '700px',
      data: { date: '', station: '', stations: this.stations, hours: hours, minutes: minutes, stopDurationMinutes: 5 }
    })

    dialogRef.afterClosed().subscribe(checkPoint => {
      if (checkPoint) {
        this.schedules.filter(schedule => schedule.scheduleId == scheduleId).map(schedule => schedule.checkPoints.push(checkPoint))
      }
    });

  }

  saveSchedule(scheduleId) {
    this.http.post('http://localhost:8485/schedules', this.schedules.filter(schedule => schedule.scheduleId == scheduleId)[0]).subscribe((savedSchedule) => {
    });
  }

  removeSchedule(scheduleId) {
    const dialogRef = this.dialog.open(DeleteScheduleConfirmDialogComponent, {
      width: '700px'
    })

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.http.delete('http://localhost:8485/schedules/' + scheduleId).subscribe((response) => {
          let index = 0
          this.schedules.forEach(schedule => {
            if (schedule.scheduleId == scheduleId) return false;
            index += 1
          });
          this.schedules.splice(index, 1)
        });
      }
    });
  }

  addSchedule() {
    let train = { 'trainName': 'NO TRAIN ASSIGNED' }
    let emptySchedule = { train: train, checkPoints: [], status: 'ON TIME', stopDurationMinnutes: 5 }
    this.schedules.unshift(emptySchedule)
  }

  onTrainChanged(scheduleId, trainId) {
    this.schedules.filter(schedule => schedule.scheduleId == scheduleId)[0].train = this.trains.filter(train => train.trainId == trainId)[0]
  }

}
