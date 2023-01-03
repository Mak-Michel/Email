import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-inbox',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent {

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  //// Moving
  destination: string

  constructor(public proxy: ProxyService) {
    proxy.getEmailList("sent").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

  move(){
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.moveEmails("Sent", this.destination, header.id).
        subscribe({
          next: (data) => {
            alert(data);
            if(data == 'Email Moved Successfully')
              this.headers.splice(this.headers.indexOf(header), 1)
          },
          error(err) {
            alert(err.message)
          }
        });
      }
    }
  }

  delete(){
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.moveEmails("Sent", "Trash", header.id).
        subscribe({
          next: (data) => {
            alert(data);
            if(data == 'Email Moved Successfully')
              this.headers.splice(this.headers.indexOf(header), 1)
          },
          error(err) {
            alert(err.message)
          }
        });
      }
    }
  }

}