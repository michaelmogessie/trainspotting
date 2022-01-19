import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteTrainConfirmDialogComponent } from '../delete-train-confirm-dialog/delete-train-confirm-dialog.component';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.scss']
})
export class TrainComponent implements OnInit {
  trains: any;
  constructor(private http: HttpClient, public dialog: MatDialog) { }

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


  saveTrain(train: any) {
    this.http.post('http://localhost:8486/trains', train).subscribe((savedTrain: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      if (!train.trainId) {
        train.trainId = savedTrain.trainId
      }
    });
  }

  addTrain() {
    if (!this.trains) {
      this.trains = [{ 'trainId': undefined, 'trainName': 'Train name not set' }]
    } else {
      this.trains.unshift({ 'trainId': undefined, 'trainName': 'Train name not set' })
    }
  }

  removeTrain(trainId: number) {
    const dialogRef = this.dialog.open(DeleteTrainConfirmDialogComponent, {
      width: '700px'
    })

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.http.delete('http://localhost:8486/trains/' + trainId).subscribe((response) => {
          let index = 0
          this.trains.forEach((train: any) => {
            if (train.trainId == trainId) return false;
            index += 1
            return true;
          });
          this.trains.splice(index, 1)
        });
      }
    });

  }

}
