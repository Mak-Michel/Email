import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-starred',
  templateUrl: './starred.component.html',
  styleUrls: ['./starred.component.css']
})
export class StarredComponent {

  headers: EmailHeader[] = [];

  constructor(proxy: ProxyService) {
    proxy.getEmailList("sent").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
