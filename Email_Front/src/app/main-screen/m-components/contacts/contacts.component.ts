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
    for(let i = 0; i < this.contacts.length; i++){
      if(this.contacts[i].selected)
        this.proxy.deleteContact(this.contacts[i].name).pipe(take(1)).subscribe();
    }
  }

}
