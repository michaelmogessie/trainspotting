import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-schedule-confirm-dialog',
  templateUrl: './delete-schedule-confirm-dialog.component.html',
  styleUrls: ['./delete-schedule-confirm-dialog.component.scss']
})
export class DeleteScheduleConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteScheduleConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
