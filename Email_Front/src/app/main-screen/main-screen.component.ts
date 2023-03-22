import { Component } from '@angular/core';
import {faFilter, faPencilAlt, faRecycle} from '@fortawesome/free-solid-svg-icons'
import {faInbox} from '@fortawesome/free-solid-svg-icons'
import {faStar} from '@fortawesome/free-solid-svg-icons'
import {faTrashAlt} from '@fortawesome/free-solid-svg-icons'
import {faBookOpen} from '@fortawesome/free-solid-svg-icons'
import {faTelegramPlane} from '@fortawesome/free-brands-svg-icons'
import {faAddressBook} from '@fortawesome/free-solid-svg-icons'
import {faSearch} from '@fortawesome/free-solid-svg-icons'
import {faFolderPlus} from '@fortawesome/free-solid-svg-icons'
import {faFolder} from '@fortawesome/free-solid-svg-icons'
import { faUndoAlt } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { ProxyService } from '../Controller/Proxy/proxy.service';
import { take } from 'rxjs';
import { ActionService } from '../Controller/Classes/action.service';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent {

  Compose = faPencilAlt;
  Inbox = faInbox;
  Starred = faStar;
  Trash = faTrashAlt;
  Draft = faBookOpen;
  Sent = faTelegramPlane;
  Contacts = faAddressBook;
  Search = faSearch;
  NewFolder = faFolderPlus;
  Folders = faFolder;
  Refresh = faUndoAlt;
  Filter = faFilter;
  Reverse = faRecycle;

  query: string
  searchType: string = "Subject"
  filterType: string = "Attachments"
  input : boolean = false;
  compared: string = ""

  constructor(public router: Router, public proxy: ProxyService, private action: ActionService) {
    proxy.getFolderList().
    subscribe(
      data => {
        proxy.setFolders(JSON.parse(data));
      }
    )
  }

  toggleInput() {
    this.input = !this.input;
  }

  public logOut() {
    this.proxy.signOut().pipe(take(1)).subscribe();
    this.router.navigate(["/registeration/login"]);
  }

  search(){
    let emailType = this.router.url.split("/")[2];
    this.action.setAction(`${emailType},search,${this.searchType.toLowerCase()},${this.query}`)
  }

  sort(event: any) {
    console.log("sorttttt")
    let sortType = event.target.value;
    let emailType = this.router.url.split("/")[2];
    this.action.setAction(`${emailType},sort,${sortType.toLowerCase()}`)
  }

  filter() {
    let emailType = this.router.url.split("/")[2];
    let bool = "";
    if(this.input) bool = "true"
    else bool = "false"
    console.log(`${emailType},filter,${this.filterType.toLowerCase()},${this.compared},${bool}`)
    this.action.setAction(`${emailType},filter,${this.filterType.toLowerCase()},${this.compared},${bool}`)
  }

  deleteFolder($event){
    this.proxy.deleteFolder($event).pipe(take(1)).subscribe()
    this.proxy.getFolders().splice(this.proxy.getFolders().indexOf($event),1)
  }

  refresh() {
    const url= this.router.url;
    this.router.navigateByUrl('/',{skipLocationChange:true}).then(()=>{
      this.router.navigate([`/${url}`]).then(()=>{
        console.log(`After navigation I am on:${this.router.url}`)
      })
    })
  }

  reverseHeaders() {
    let emailType = this.router.url.split("/")[2];
    console.log(`${emailType},reverseHeaders`)
    this.action.setAction(`${emailType},reverseHeaders`)
  }

}
