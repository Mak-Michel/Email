import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-sent',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent {
//   window.addEventListener('beforeunload', function (event) {
//     if (performance.navigation.type == 1) {
//       document.write('Window was refreshed!');
//     } 
//     else {
//       document.write('Window was closed!');
//     }
// });
  headers: EmailHeader[] = [];

  constructor(router: Router, proxy: ProxyService) {
    if(proxy.getUser() == "") {
      router.navigate(["/registeration/login"]);
      return;
    }
    proxy.getEmailList("sent").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

}
