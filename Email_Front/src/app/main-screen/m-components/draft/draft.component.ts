import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.css']
})
export class DraftComponent {

  headers: EmailHeader[] = [];

  constructor(proxy: ProxyService) {
    proxy.getEmailList("draft").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
