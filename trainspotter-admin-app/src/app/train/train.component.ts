import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TabchangeService } from '../services/tabchange.service';

const trainNames = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQR', 'STU', 'VWX', 'YZA'];

@Component({
  selector: 'app-train',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.scss']
})

export class TrainComponent implements OnInit {
  displayedColumns: string[] = ['trainId', 'trainName'];
  dataSource: any = [];
  tabIndex = 1;
  constructor(private http: HttpClient, private tabChangeService: TabchangeService) { }
  ngOnInit(): void {
    this.tabChangeService.tabChange.subscribe((tabIndex: number) => {
      if (this.tabIndex === tabIndex) {
        this.getTrains();
      }
    });
  }

  getTrains() {
    this.http.get('http://localhost:30032/trains').subscribe((trains: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.dataSource = trains['_embedded'].trainList;
    });
  }

  addTrain() {
    const train = {};
    // eslint-disable-next-line @typescript-eslint/dot-notation
    train['trainName'] = trainNames[Math.round(Math.random() * 10)] + '_' + Math.floor((Math.random() * 10));
    this.http.post('http://localhost:30032/trains', train)
      .subscribe((savedTrain: any) => {
        // eslint-disable-next-line @typescript-eslint/dot-notation
        this.dataSource.push(savedTrain);
        this.dataSource = [...this.dataSource];
      });
  }

}
