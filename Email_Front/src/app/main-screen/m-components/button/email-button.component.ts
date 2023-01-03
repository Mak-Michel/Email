import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-email_button',
  templateUrl: './email-button.component.html',
  styleUrls: ['./email-button.component.css']
})
export class EmailButtonComponent implements OnInit {

  @Input('header') emailHeader: EmailHeader | undefined;
  @Input('router') router: string = "email";


  ngOnInit(): void {
  }

  public returnId(){
    
  }

  public select(){
    this.emailHeader.selected = !this.emailHeader.selected;
    console.log(this.emailHeader.selected)
  }

}