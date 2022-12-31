import { faTrashAlt, faSave, faEdit } from '@fortawesome/free-solid-svg-icons';
import { Component } from '@angular/core';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent {

  Trash = faTrashAlt;
  Save = faSave;
  Edit = faEdit;

}
