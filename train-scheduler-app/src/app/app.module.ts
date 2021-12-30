import { AppComponent } from './app.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { TrainComponent } from './train/train.component';
import { StationComponent } from './station/station.component';
import { NgModule } from '@angular/core';
import { CoreModule } from './core/core.module';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { MaterialsModule } from './materials/materials.module';
@NgModule({
  declarations: [
    AppComponent,
    ScheduleComponent,
    TrainComponent,
    StationComponent
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
