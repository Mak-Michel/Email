import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-attachment2',
  templateUrl: './attachment2.component.html',
  styleUrls: ['./attachment2.component.css']
})
export class Attachment2Component {
  constructor(private proxy: ProxyService){}

  @Input('name') attachmentName: string

  /*downloadfile() {
    console.log(this.attachmentName);
    this.proxy.download(this.attachmentName).subscribe(data => {
      //console.log(data);
      let savedfile = data.body;
      let element = document.createElement('a');
      element.download=this.attachmentName;
      element.href = window.URL.createObjectURL(savedfile);
      element.click();
    })
  }*/

  downloadfile(): void {
    this.proxy.download(this.attachmentName).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      })
  }

  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch(httpEvent.type) {
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent); 
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
        } else {
          saveAs(new File([httpEvent.body!], this.attachmentName.substring(15), 
                  {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
          // saveAs(new Blob([httpEvent.body!], 
          //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
          //    httpEvent.headers.get('File-Name'));
        }
        break;
        default:
          console.log(httpEvent);
          break;
      
    }
  }
}
