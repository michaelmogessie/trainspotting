import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-station-confirm-dialog',
  templateUrl: './delete-station-confirm-dialog.component.html',
  styleUrls: ['./delete-station-confirm-dialog.component.scss']
})
export class DeleteStationConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteStationConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
