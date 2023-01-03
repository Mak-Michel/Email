import { Component } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { Contact } from 'src/app/Controller/Classes/Contact';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent {

  constructor(private proxy: ProxyService){}

  Save = faSave

  name: string = ""
  emails: string = ""

  public saveContact(){
    this.emails.split(/[\s,]+/)
    let emailList: string[] = this.emails.split(/[\s,]+/);
    let newContact: Contact = new Contact(this.name, emailList);
    console.log(newContact)
    this.proxy.addContact(newContact).pipe(take(1)).subscribe();
  }
}
