import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

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

  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  /////

  headers: EmailHeader[] = [];

  constructor(private proxy: ProxyService) {
    proxy.getEmailList("sent").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

  delete() {
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.trashEmail(header.id).
        subscribe({
          next: (data) => {
            alert(data);
          },
          error(err) {
            alert(err.message)
          }
        });
      }
    }
    
  }

}
