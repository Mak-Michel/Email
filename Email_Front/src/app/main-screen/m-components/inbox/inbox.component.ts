import { Component } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent {

  title = new EmailHeader("g6UunN0waqBz9mW" , "bye" , "Marwan" , ["Ehab"] , "myasshole" ,  10202020 , false);
  header1 = new EmailHeader("OcWlpG7NbldGGkt", "helllo sjfhgedfhjef mhgefhjef", "Marwan", ["Mkario"], "myasshole", 10202020, false);
  headers: EmailHeader[] = [this.title, this.header1, this.title, this.title, this.title, this.title, this.title, this.title, this.title, this.header1, this.title, this.title, this.title, this.title, this.title]


}
