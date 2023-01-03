import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faTelegramPlane } from '@fortawesome/free-brands-svg-icons';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { Contact } from 'src/app/Controller/Classes/Contact';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-contact-display',
  templateUrl: './contact-display.component.html',
  styleUrls: ['./contact-display.component.css']
})


export class ContactDisplayComponent implements OnInit{
  contactName: string | undefined
  contact: Contact | undefined

  Sent = faTelegramPlane;
  Trash = faTrashAlt

  constructor(private _route: ActivatedRoute, private proxy: ProxyService){}

  ngOnInit() {
    this._route.params.pipe(take(1)).subscribe(params => {
        this.contactName = params['contactName'];
        this.proxy.getContact(this.contactName).pipe(take(1)).subscribe(
          data => {
            this.contact = JSON.parse(data);
          }
        )
        })
  }

  deleteContact($event){
    this.contact.userEmails.splice(this.contact.userEmails.indexOf($event), 1)
    this.proxy.editContact(this.contact).pipe(take(1)).subscribe();
  }
}
