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
  attachments: string[] = []

  constructor(private _route: ActivatedRoute, private proxy: ProxyService){}

  ngOnInit() {
    this._route.params.pipe(take(1)).subscribe(params => {
        this.receivers = params['reciever'];
        if(params['emailId'] == undefined)
          return
        this.emailId = params['emailId'];
        this.proxy.getEmail(this.emailId).pipe(take(1)).subscribe(
          data => {
            this.email = JSON.parse(data);
            this.receivers = ""
            for(let i = 0; i < this.email.receivers.length; i++)
              this.receivers += this.email.receivers[i] + '\t'
            this.subject = this.email.subject
            this.emailBody = this.email.emailBody
            this.priority = String(this.email.priority)
            this.attachments = this.email.attachments_IDS;
          }
        )
    })
  }

  sendEmail(){
    if(this.receivers == undefined || this.receivers == '' || this.subject == undefined || this.subject == '')
      return
    let receiversArr: string[];
    this.receivers = this.receivers.replaceAll('@mymail.com', '')
    receiversArr = this.receivers.split(/[\s,]+/)
    for(let i = 0; i < receiversArr.length; i++){
      if(receiversArr[i] == this.proxy.currentUser){
        alert("Sending E-mail to self")
        return
      }
    }
    if(this.attachments == undefined || this.attachments.length == 0)
      this.attachments = []
    let newEmail = new Email(this.emailId, this.emailBody, this.proxy.currentUser, receiversArr, this.subject, '0', false, Number(this.priority), this.attachments);
    this.proxy.createNewEmail(newEmail).pipe(take(1)).subscribe()
  }

  draft(){
    let receiversArr: string[];
    if(this.receivers == undefined || this.receivers == ''){
      this.proxy.createDraft([this.emailId, this.subject, this.emailBody, this.priority, this.proxy.currentUser, undefined].concat([String(this.attachments.length)]).concat(this.attachments)).pipe(take(1)).subscribe()
      return
    }
    receiversArr = this.receivers.split(/[\s,]+/)
    this.receivers = this.receivers.replaceAll('@mymail.com', '')
    this.proxy.createDraft([this.emailId, this.subject, this.emailBody, this.priority, this.proxy.currentUser, String(receiversArr.length)].concat(receiversArr).concat([String(this.attachments.length)]).concat(this.attachments)).pipe(take(1)).subscribe()
  } 

  Uploadfile(e: any) {
    if(e.target.files) {
      let files = e.target.files
      let formData = new FormData();
      for(let i = 0; i < files.length ; i++) {
        formData.append('attachments', files[i], files[i].name);
      }
      console.log(this.attachments);
      console.log(files.length);

      this.proxy.upload(formData).
      subscribe(data => {
        if(data == undefined)
          return
        this.attachments = this.attachments.concat(JSON.parse(data));
      })
    }
  }

  removeAttachment($event){
    this.attachments.splice(this.attachments.indexOf($event),1);
  }
 
}
