import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';

@Component({
  selector: 'app-inbox',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent {

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  //// Moving
  destination: string

  constructor(public proxy: ProxyService) {
    proxy.getEmailList("trash").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

  move(){
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.moveEmails("Trash", this.destination, header.id).
        subscribe({
          next: (data) => {
            if(data == 'Email Moved Successfully'){
              this.headers.splice(this.headers.indexOf(header), 1)
            }
          },
          error(err) {
            alert(err.message)
          }
        });
      }
    }
  }

  restore(){
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.restoreEmail(header.id).
        subscribe({
          next: (data) => {
            if(data == 'Email Restored Successfully'){
              this.headers.splice(this.headers.indexOf(header), 1)
            }
          },
          error(err) {
            alert(err.message)
          }
        });
      }
    }
  }
  delete(){
    if(!confirm('This action will permenantly delete these file. Are you sure you want to proceed?'))
      return
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.deleteEmail(header.id).pipe(take(1)).subscribe();
        this.headers.splice(this.headers.indexOf(header), 1);
      }
    }
  }

}