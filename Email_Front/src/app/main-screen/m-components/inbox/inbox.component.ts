import { Component, OnInit } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';
import { take } from 'rxjs';

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
  //// Moving
  destination: string
  // Searching
  function: string
  functionParameter: string

  constructor(public proxy: ProxyService, public _router: Router, public _route: ActivatedRoute) {
    this._router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
          console.log('hi change detected');
      }

      if (event instanceof NavigationEnd) {
        this._route.params.pipe(take(1)).subscribe(params => {
          this.function = params['function'];
          this.functionParameter = params['functionParameter'];
          if(this.function == 'search'){

          }
          else if(this.function == 'sort')
          {
            this.sort(this.functionParameter)
          }
          else{
            proxy.getEmailList("inbox").
            subscribe(
              data => {
                this.headers = JSON.parse(data);
              }
            )
          }
          })
      }

      if (event instanceof NavigationError) {
          console.log(event.error);
      }
  });
  }
  move(){
    for(let header of this.headers) {
      if(header.selected) {
        this.proxy.moveEmails("Inbox", this.destination, header.id).
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
        this.proxy.moveEmails("Inbox", "Trash", header.id).
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

  sort(sortType: string) {
    this.proxy.sortEmails('inbox', sortType)
    .subscribe({
      next: (data) => {
        this.headers = JSON.parse(data);
      },
      error(err) {
        alert(err.error)
      }
    });
    console.log(sortType)
  }

}
