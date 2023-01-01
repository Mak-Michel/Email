import { Component, HostListener } from '@angular/core';
import { take } from 'rxjs';
import { ProxyService } from './Controller/Proxy/proxy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mail';

  constructor(private proxy: ProxyService) { }

  @HostListener('window:beforeunload', [ '$event' ])
  beforeUnloadHandler(event) {
    this.proxy.signOut().pipe(take(1)).subscribe();
    event.preventDefault();
    event.returnValue = '';
  }

}
