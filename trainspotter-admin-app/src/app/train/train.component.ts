import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.scss']
})

export class TrainComponent implements OnInit {
  displayedColumns: string[] = ['trainId', 'trainName'];
  dataSource: any = [];

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.getTrains();
  }

  getTrains() {
    this.http.get('http://localhost:30032/trains').subscribe((trains: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.dataSource = trains['_embedded'].trainList;
    });
  }

}
