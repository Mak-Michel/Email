import { Component, OnInit } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ActionService } from 'src/app/Controller/Classes/action.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-inbox',
  templateUrl: './trash.component.html',
  styleUrls: ['./trash.component.css']
})
export class TrashComponent implements OnInit{

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  //// Moving
  destination: string

  constructor(public proxy: ProxyService, private action: ActionService) {
    proxy.getEmailList("trash").
    subscribe(
      data => {
        this.headers = JSON.parse(data);
      }
    )
  }

  ngOnInit(): void {
    this.action.$action.subscribe({
      next: (action: string) => {
        console.log(action)
        let actions = action.split(",");
        if(actions[0] != "trash") return;
        switch(actions[1]) {
          case 'sort': 
            this.sort(actions[2]);
            break;
            case 'search':
              this.search(actions[2], actions[3]);
              break;
            case 'filter':
              this.filter(actions[2], actions[3], actions[4]);
              break;
            case 'reverseHeaders':
              this.headers = this.headers.reverse()
        }
      }
    })
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

  sort(sortType: string) {
    console.log(sortType)
    this.proxy.sortEmails('trash', sortType)
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

  search(searchType, searchKey) {
    console.log(searchType)
    this.proxy.searchEmails('trash', searchType, searchKey)
    .subscribe({
      next: (data) => {
        this.headers = JSON.parse(data);
      },
      error(err) {
        alert(err.error)
      }
    });
  }

  filter(filterType: string, compared: string, bool: string) {
    console.log(filterType)
    this.proxy.filterEmails('trash', filterType, compared, bool)
    .subscribe({
      next: (data) => {
        this.headers = JSON.parse(data);
      },
      error(err) {
        alert(err.error)
      }
    });
  }

}