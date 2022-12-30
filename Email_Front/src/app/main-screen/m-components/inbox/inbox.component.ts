import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent {

  headers: EmailHeader[] = [];

  constructor(proxy: ProxyService) {
    proxy.getEmailList("inbox").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
