import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-checkpoint-dialog',
  templateUrl: './add-checkpoint-dialog.component.html',
  styleUrls: ['./add-checkpoint-dialog.component.scss']
})
export class AddCheckpointDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AddCheckpointDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }
  hours: any
  minutes: any
  stations = []
  hour: any
  minute: any
  date: any
  stationId: any
  stopDurationMinutes: number
  ngOnInit(): void {
    this.stations = this.data.stations
    this.hours = this.data.hours
    this.minutes = this.data.minutes
    this.stopDurationMinutes = this.data.stopDurationMinutes;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  setData() {
    if (!this.date) return
    let checkPoint = { 'station': this.stations.filter(station => station.stationId == this.stationId)[0], 'eta': {}, 'stopDurationMinutes': this.stopDurationMinutes }
    let dateTime = new Date(this.date)
    let month = dateTime.getMonth() + 1;
    let monthStr = month < 10 ? '0' + month : '' + month
    checkPoint.eta = dateTime.getFullYear() + '-' + monthStr + '-' + dateTime.getDate() + ' ' + this.hour + ':' + this.minute
    this.data = checkPoint
    this.dialogRef.close(this.data)
  }

}
