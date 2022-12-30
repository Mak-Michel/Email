import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-trash',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent {

  headers: EmailHeader[] = [];

  constructor(proxy: ProxyService) {
    proxy.getEmailList("trashed").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
