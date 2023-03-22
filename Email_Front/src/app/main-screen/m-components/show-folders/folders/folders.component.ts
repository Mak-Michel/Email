import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { ActivatedRoute, Event, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-folders',
  templateUrl: './folders.component.html',
  styleUrls: ['./folders.component.css'],
})
export class FoldersComponent{
  headers: EmailHeader[] = [];
  folderName: string = ""
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  //// Moving
  destination: string

  constructor(private _route: ActivatedRoute, public proxy: ProxyService) {
    console.log('Route afsafsdf detected');
    this._route.params.pipe(take(1)).subscribe(params => {
      this.folderName = params['folder-name'];
      this.proxy.getEmailList(this.folderName).pipe(take(1)).subscribe(
        data => {
          console.log(data);
          if(data == null || data == undefined)
            return
          this.headers = JSON.parse(data);
        }
      )
      })
  } 
move(){
  for(let header of this.headers) {
    if(header.selected) {
      this.proxy.moveEmails(this.folderName, this.destination, header.id).
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
      this.proxy.moveEmails(this.folderName, "Trash", header.id).
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
