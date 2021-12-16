import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.scss']
})

export class TrainComponent implements OnInit {
  constructor(private http: HttpClient) { }
  displayedColumns: string[] = ['trainId', 'trainName'];
  dataSource: any = []
  ngOnInit(): void {
    this.getTrains();
  }

  getTrains() {
    this.http.get('http://localhost:30032/trains').subscribe((trains: any) => {
      this.dataSource = trains['_embedded']['trainList'];
      console.table(this.dataSource)
    })
  }

}
