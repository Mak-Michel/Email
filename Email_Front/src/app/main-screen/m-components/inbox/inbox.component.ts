import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent {

  headers: EmailHeader[] = [];

  constructor(router: Router, proxy: ProxyService) {
    if(proxy.getUser() == "") {
      router.navigate(["/registeration/login"]);
      return;
    }
    proxy.getEmailList("inbox").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
