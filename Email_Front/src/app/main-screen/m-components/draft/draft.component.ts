import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.css']
})
export class DraftComponent {

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  /////

  constructor(router: Router, proxy: ProxyService) {
    proxy.getEmailList("draft").
    subscribe(
      data => {
        alert(data)
        this.headers = JSON.parse(data);
      }
    )
  }
}
