import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-train-confirm-dialog',
  templateUrl: './delete-train-confirm-dialog.component.html',
  styleUrls: ['./delete-train-confirm-dialog.component.scss']
})
export class DeleteTrainConfirmDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteTrainConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
