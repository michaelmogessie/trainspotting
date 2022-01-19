import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DeleteStationConfirmDialogComponent } from '../delete-station-confirm-dialog/delete-station-confirm-dialog.component';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.scss']
})
export class StationComponent implements OnInit {

  stations: any;
  constructor(private http: HttpClient, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.getStations();
  }

  getStations() {
    this.http.get('http://localhost:8487/stations').subscribe((stations: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      let stationList = stations['_embedded'];
      if (stationList) {
        this.stations = stationList.stationList;
      }
    });
  }

  saveStation(station: any) {
    this.http.post('http://localhost:8487/stations', station).subscribe((savedStation: any) => {
      // eslint-disable-next-line @typescript-eslint/dot-notation
      if(!station.stationId) {
        station.stationId = savedStation.stationId
      }
    });
  }

  addStation() {
    if (!this.stations) {
      this.stations = [{ 'stationId': undefined, 'stationName': 'Station name not set' }]
    } else {
      this.stations.unshift({ 'stationId': undefined, 'stationName': 'Station name not set' })
    }
  }

  removeStation(stationId: number) {
    const dialogRef = this.dialog.open(DeleteStationConfirmDialogComponent, {
      width: '700px'
    })

    dialogRef.afterClosed().subscribe(confirmed => {
      if (confirmed) {
        this.http.delete('http://localhost:8487/stations/' + stationId).subscribe((response) => {
          let index = 0
          this.stations.forEach((station: any) => {
            if (station.stationId == stationId) return false;
            index += 1
            return true;
          });
          this.stations.splice(index, 1)
        });
      }
    });

  }

}
