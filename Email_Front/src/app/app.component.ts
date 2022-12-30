import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { ProxyService } from './Controller/Proxy/proxy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mail';

  constructor(private router: Router, private proxy: ProxyService) { }

  @HostListener('window:beforeunload', [ '$event' ])
  beforeUnloadHandler(event) {
    console.log("dsf")
    this.proxy.signOut().pipe(take(1)).subscribe();
    this.router.navigate(["/registeration/signin"]);
    event.preventDefault();
    event.returnValue = '';
  }

}
