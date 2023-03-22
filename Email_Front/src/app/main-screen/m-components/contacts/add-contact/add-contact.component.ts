import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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

  contact: Contact = new Contact();

  constructor(private proxy: ProxyService, private _route: ActivatedRoute){
    this._route.params.pipe(take(1)).subscribe(params => {
      if(params['contactName'] == undefined || params['contactName'] == '')
        return
      this.proxy.getContact(params['contactName']).pipe(take(1)).subscribe(
        data => {
          this.contact = JSON.parse(data)
          this.emails = this.contact.userEmails.toString().replace(',', '\n')
        }
      )
    })
  }

  Save = faSave

  emails: string = ""

  public saveContact(){
    this.emails.split(/[\s,]+/)
    let emailList: string[] = this.emails.split(/[\s,]+/);
    if(this.contact.userEmails != undefined && this.contact.userEmails.length != 0){
      this.contact.userEmails  = emailList
      this.proxy.editContact(this.contact).pipe(take(1)).subscribe();
      return
    }
    this.contact.userEmails  = emailList
    console.log(this.contact)
    this.proxy.addContact(this.contact).pipe(take(1)).subscribe();
  }
}
