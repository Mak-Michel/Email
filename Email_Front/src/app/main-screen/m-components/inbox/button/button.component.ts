import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EmailHeader } from 'src/app/Controller/Classes/EmailHeader';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  @Input('header') emailHeader: EmailHeader | undefined;
  @Output() onClick: EventEmitter<string> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  public returnId(){
    if(!(this.emailHeader == undefined))
      this.onClick.emit(this.emailHeader.id);
    this.onClick.emit("");
  }

}