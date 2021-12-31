import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddCheckpointDialogComponent } from './add-checkpoint-dialog/add-checkpoint-dialog.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { DeleteScheduleConfirmDialogComponent } from './delete-schedule-confirm-dialog/delete-schedule-confirm-dialog.component';
import { MaterialsModule } from './materials/materials.module';
import { ScheduleComponent } from './schedule/schedule.component';
import { StationComponent } from './station/station.component';
import { TrainComponent } from './train/train.component';

@NgModule({
  declarations: [
    AppComponent,
    ScheduleComponent,
    TrainComponent,
    StationComponent,
    AddCheckpointDialogComponent,
    DeleteScheduleConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule,
    CoreModule,
    MaterialsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
