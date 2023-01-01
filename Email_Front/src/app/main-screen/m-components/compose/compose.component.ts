import { Component } from '@angular/core';
import { faPaperclip } from '@fortawesome/free-solid-svg-icons'
import { faSave } from '@fortawesome/free-solid-svg-icons'
import {faTelegramPlane} from '@fortawesome/free-brands-svg-icons'
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent {

  paperClip = faPaperclip;
  save = faSave;
  send = faTelegramPlane;

  constructor(private router: Router, private proxy: ProxyService) { }

  private to: string = "";
  private subject: string = "";
  private priority: number = 1;
  private body: string = "";

  public getTo(to: any) {
    this.to = to.target.value;
    console.log(this.to);
  }

  public getSubject(subject: any) {
    this.subject = subject.target.value;
    console.log(this.subject);
  }

  public getPriority(priority: any) {
    this.priority = priority.target.value;
    console.log(this.priority);
  }

  public getBody(body: any) {
    this.body = body.target.value;
    console.log(this.body);
  }

  public new() {
    this.proxy.createNewEmail(this.body, this.to, this.subject, this.priority).
    subscribe({
      next: (data) => {
        alert(data);
        this.router.navigate(["/main-screen/inbox"]);
      },
      error(err) {
        alert(err.error)
      }
    });
  }

  draft() {
  //   this.proxy.(this.body, this.to, this.subject, this.priority).
  //   subscribe({
  //     next: (data) => {
  //       alert(data);
  //       this.router.navigate(["/main-screen/inbox"]);
  //     },
  //     error(err) {
  //       alert(err.error)
  //     }
  //   });
  }

}
