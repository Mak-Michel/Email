import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { Contact } from 'src/app/Controller/Classes/Contact';

@Component({
  selector: 'app-button3',
  templateUrl: './button3.component.html',
  styleUrls: ['./button3.component.css']
})
export class Button3Component {
  
  @Input("contactName") contact: Contact | undefined

  constructor(private router: Router){}

  Edit = faEdit;
  select(){
    this.contact.selected = !this.contact.selected;
    console.log(this.contact.selected)
  }
}
