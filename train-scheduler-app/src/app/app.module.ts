import { AppComponent } from './app.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { TrainComponent } from './train/train.component';
import { StationComponent } from './station/station.component';
import { NgModule } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CoreModule } from './core/core.module';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    ScheduleComponent,
    TrainComponent,
    StationComponent
  ],
  imports: [
    MatTabsModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CommonModule,
    CoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
