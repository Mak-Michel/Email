import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { take } from 'rxjs';
import { Email } from 'src/app/Controller/Classes/Email';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-messaege',
  templateUrl: './messaege.component.html',
  styleUrls: ['./messaege.component.css']
})
export class MessaegeComponent implements OnInit{

  public emailId: string;
  public email: Email | undefined

  constructor(private _route: ActivatedRoute, private proxy: ProxyService){}

  ngOnInit() {
    this._route.params.pipe(take(1)).subscribe(params => {
      this.emailId = params['emailId'];
      this.proxy.getEmail(this.emailId).pipe(take(1)).subscribe(
        data => {
          this.email = JSON.parse(data);
        }
      )
    })
  }
}
