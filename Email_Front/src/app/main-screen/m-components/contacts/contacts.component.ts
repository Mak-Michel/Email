import { faTrashAlt, faSave } from '@fortawesome/free-solid-svg-icons';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { take } from 'rxjs';
import { Contact } from 'src/app/Controller/Classes/Contact';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent {

  Trash = faTrashAlt;
  Save = faSave;

  contacts: Contact[] = []

  constructor(private router: Router, private proxy: ProxyService) {
    proxy.getContactList().pipe(take(1)).
    subscribe(
      data => {
        this.contacts = JSON.parse(data);
      }
    )
  }

  deleteContacts(){
    if(!confirm(`Are you sure you want to remove these contacts forever?`))
      return
    for(let i = 0; i < this.contacts.length; i++){
      if(this.contacts[i].selected){
        this.proxy.deleteContact(this.contacts[i].name).pipe(take(1)).subscribe();
        this.contacts.splice(this.contacts.indexOf(this.contacts[i]), 1)
      }
    }
  }

  delete($event){
    if(confirm(`Are you sure you want to remove ${$event} from your contacts forever?`)){
      this.proxy.deleteContact($event).pipe(take(1)).subscribe();
      this.contacts.splice(this.contacts.indexOf($event), 1)
    }
  }

}
