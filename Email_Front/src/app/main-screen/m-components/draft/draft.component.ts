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

  constructor(private proxy: ProxyService) {
    proxy.getEmailList("draft").
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
