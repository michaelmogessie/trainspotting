import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CommonModule } from '@angular/common';
import { CoreModule } from './core/core.module';
import { MaterialsModule } from './materials/materials.module';
import { TrainComponent } from './train/train.component';
import { StationComponent } from './station/station.component';
import { DeleteStationConfirmDialogComponent } from './delete-station-confirm-dialog/delete-station-confirm-dialog.component';
import { DeleteTrainConfirmDialogComponent } from './delete-train-confirm-dialog/delete-train-confirm-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainComponent,
    StationComponent,
    DeleteStationConfirmDialogComponent,
    DeleteTrainConfirmDialogComponent
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
