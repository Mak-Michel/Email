import { Component, OnInit } from '@angular/core';
import { faPaperclip } from '@fortawesome/free-solid-svg-icons'
import { faSave } from '@fortawesome/free-solid-svg-icons'
import {faTelegramPlane} from '@fortawesome/free-brands-svg-icons'
import { ActivatedRoute } from '@angular/router';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { take, throwError } from 'rxjs';
import { Email } from 'src/app/Controller/Classes/Email';


@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  paperClip = faPaperclip;
  save = faSave;
  send = faTelegramPlane;

  emailId: string = ""
  email: Email | undefined

  priority: string = ""
  subject: string = ""
  receivers: string = ""
  emailBody: string = ""
  attachments_name: Array<string>;

  constructor(private _route: ActivatedRoute, private proxy: ProxyService){}

  ngOnInit() {
    this._route.params.pipe(take(1)).subscribe(params => {
        this.receivers = params['reciever'];
        this.emailId = params['emailId'];
        if(this.emailId.length != 15)
          return
        this.proxy.getEmail(this.emailId).pipe(take(1)).subscribe(
          data => {
            this.email = JSON.parse(data);
            this.receivers = ""
            for(let i = 0; i < this.email.receivers.length; i++)
              this.receivers += this.email.receivers[i] + '\t'
            this.subject = this.email.subject
            this.emailBody = this.email.emailBody
            this.priority = String(this.email.priority)
          }
        )
    })
  }

  sendEmail(){
    if(this.receivers == undefined || this.receivers == '' || this.subject == undefined || this.subject == '')
      return
    let receiversArr: string[];
    receiversArr = this.receivers.split(/[\s,]+/)
    for(let i = 0; i < receiversArr.length; i++){
      if(receiversArr[i] == this.proxy.currentUser){
        alert("Sending E-mail to self")
        return
      }
    }
    let newEmail = new Email(this.emailId, this.emailBody, this.proxy.currentUser, receiversArr, this.subject, 0, false, Number(this.priority));
    this.proxy.createNewEmail(newEmail).pipe(take(1)).
    subscribe({
      next: (data) => {
        alert(data)
      },
      error(err) {
        alert(err.error)
      }
    });
  }

  draft(){
    let receiversArr: string[];
    if(this.receivers == undefined || this.receivers == ''){
      this.proxy.createDraft([this.emailId, this.subject, this.emailBody, this.priority, this.proxy.currentUser, undefined]).pipe(take(1)).subscribe()//.concat([String(receiversArr.length), '']))
      return
    }
    receiversArr = this.receivers.split(/[\s,]+/)
    this.proxy.createDraft([this.emailId, this.subject, this.emailBody, this.priority, this.proxy.currentUser, String(receiversArr.length)].concat(receiversArr)).pipe(take(1)).subscribe()//.concat([String(receiversArr.length), '']))
  }

  Uploadfile(e: any) {
    alert("a7a")
    if(e.target.files) {
      this.attachments_name = new Array();
      let files = e.target.files
      let formData = new FormData();
      for(let i = 0; i < files.length ; i++) {
        formData.append('attachments', files[i], files[i].name);
        this.attachments_name.push(files[i].name);
      }
      console.log(this.attachments_name);
      console.log(files.length);

      this.proxy.upload(formData).
      subscribe(data => {
        alert(data);
      })
      /*
      let reader = new FileReader();
      reader.readAsBinaryString(e.target.files[0]);
      //console.log(e.target.files[0]);
      var path = (window.URL || window.webkitURL).createObjectURL(e.target.files[0]);
      //console.log(path);
      reader.onload=(_e) => {
        this.url = reader.result;
        //console.log(this.url.length);
        //console.log(this.url);
      }*/
    }
  }

}
