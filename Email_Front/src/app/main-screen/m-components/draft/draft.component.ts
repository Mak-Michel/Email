import { Component, OnInit } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { ActionService } from 'src/app/Controller/Classes/action.service';

@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.css']
})
export class DraftComponent implements OnInit{

  headers: EmailHeader[] = [];
  
  //// Mak
  Trash = faTrashAlt;
  p: number = 1;
  /////

  constructor(private proxy: ProxyService, private action: ActionService) {
    proxy.getEmailList("draft").
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
        if(actions[0] != "draft") return;
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
  sort(sortType: string) {
    console.log(sortType)
    this.proxy.sortEmails('draft', sortType)
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
    this.proxy.searchEmails('draft', searchType, searchKey)
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
    this.proxy.filterEmails('draft', filterType, compared, bool)
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
