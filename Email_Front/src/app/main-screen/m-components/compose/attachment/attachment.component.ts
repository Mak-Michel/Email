import { Component, EventEmitter, Input, Output } from '@angular/core';
import { faMinusCircle } from '@fortawesome/free-solid-svg-icons';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-attachment',
  templateUrl: './attachment.component.html',
  styleUrls: ['./attachment.component.css']
})
export class AttachmentComponent {

  constructor(private proxy: ProxyService){}

  @Input('name') attachmentName: string
  @Output('remove') removeAttach = new EventEmitter<String>

  remove = faMinusCircle

  attachments_name: Array<string>;
  
  removeAttachment(){
    this.removeAttach.emit(this.attachmentName);
  }

}
