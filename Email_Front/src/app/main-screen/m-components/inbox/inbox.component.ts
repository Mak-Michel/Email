import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent {

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  /////

  constructor(private proxy: ProxyService) {
    proxy.getEmailList("inbox").
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
