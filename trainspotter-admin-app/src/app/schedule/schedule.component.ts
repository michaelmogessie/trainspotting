/* eslint-disable @typescript-eslint/dot-notation */
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TabchangeService } from '../services/tabchange.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {
  displayedColumns: string[] = ['scheduleId', 'departureTime', 'arrivalTime', 'trainName'];
  tabIndex = 0;
  schedules = [];
  trains = [];
  stations = [];
  constructor(private http: HttpClient, private tabChangeService: TabchangeService) { }

  ngOnInit(): void {
    this.getSchedules();
    this.tabChangeService.tabChange.subscribe((tabIndex: number) => {
      if (this.tabIndex === tabIndex) {
        this.getSchedules();
      }
    });
    this.tabChangeService.trains.subscribe((trains: any) => {
      this.trains = trains;
    });
    this.tabChangeService.stations.subscribe((stations: any) => {
      this.stations = stations;
    });
  }

  getSchedules() {
    this.http.get('http://localhost:30031/schedules').subscribe((schedules: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      if (schedules['_embedded']) {
        // eslint-disable-next-line @typescript-eslint/dot-notation
        this.schedules = schedules['_embedded'].scheduleList;
      }
    });
  };

  addSchedule() {
    const schedule = {};
    schedule['departureTime'] = Date.now();
    schedule['arrivalTime'] = Date.now();
    schedule['train'] = this.trains[0];
    schedule['stations'] = this.stations;
    console.table(schedule);
    this.http.post('http://localhost:30031/schedules', schedule).subscribe((savedSchedule: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      this.schedules.push(savedSchedule);
      this.schedules = [...this.schedules];
    });
  };

}
