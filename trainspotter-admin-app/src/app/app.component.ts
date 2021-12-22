import { Component } from '@angular/core';
import { TabchangeService } from './services/tabchange.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'trainspotter-admin-app';

  constructor(private tabChangeService: TabchangeService) { }

  onTabChanged($event: any) {
    this.tabChangeService.emitTabChangeEvent($event.index);
  }

}

