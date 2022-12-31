import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input('header') emailHeader: EmailHeader | undefined;
  @Output() onClick: EventEmitter<string> = new EventEmitter();

  constructor(private proxy: ProxyService) { }

  ngOnInit(): void {
  }

  public returnId(){
    console.log(this.emailHeader.id);
    if(!(this.emailHeader == undefined))
      this.proxy.getEmail(this.emailHeader.id).subscribe(data => console.log(data));
    this.onClick.emit("");
  }

  public select(){
    this.emailHeader.selected = !this.emailHeader.selected;
    console.log(this.emailHeader.selected)
  }

}