import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-trash',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent {

  headers: EmailHeader[] = [];

  constructor(router: Router, proxy: ProxyService) {
    if(proxy.getUser() == "") {
      router.navigate(["/registeration/login"]);
      return;
    }
    proxy.getEmailList("trashed").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
