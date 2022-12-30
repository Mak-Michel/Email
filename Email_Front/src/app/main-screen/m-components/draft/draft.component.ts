import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.css']
})
export class DraftComponent {

  headers: EmailHeader[] = [];

  constructor(router: Router, proxy: ProxyService) {
    if(proxy.getUser() == "") {
      router.navigate(["/registeration/login"]);
      return;
    }
    proxy.getEmailList("draft").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
