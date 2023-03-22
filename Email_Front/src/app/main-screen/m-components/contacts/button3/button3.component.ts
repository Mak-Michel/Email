import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { Contact } from 'src/app/Controller/Classes/Contact';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-button3',
  templateUrl: './button3.component.html',
  styleUrls: ['./button3.component.css']
})
export class Button3Component {

  Trash = faTrashAlt;
  
  @Input("contactName") contact: Contact | undefined
  @Output("delete") contactDeleted = new EventEmitter<string> 

  constructor(private router: Router, private proxy: ProxyService){}

  Edit = faEdit;
  select(){
    this.contact.selected = !this.contact.selected;
    console.log(this.contact.selected)
  }
  edit(){
    this.router.navigate([`/main-screen/add-contact/${this.contact.name}`]); 
  }
  delete(){
    this.contactDeleted.emit(this.contact.name);
  }
}
