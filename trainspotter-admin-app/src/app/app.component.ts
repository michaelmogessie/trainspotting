import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'trainspotter-admin-app';

  onTabChanged($event: any) {
    console.log($event.index);
  }

}

