import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private router: Router, private proxy: ProxyService) { }

  private name: string = "";
  private email: string = "";
  private password: string = "";

  getName(name : any) {
    this.name = name.target.value;
  }
  getEmail(email : any) {
    this.email = email.target.value;
  }
  getPassword(password : any) {
    this.password = password.target.value;
  }

  signUp() {
    this.proxy.signUp(this.name, this.email, this.password).
    subscribe({
      next: (data) => {
        this.proxy.setUser(data);
        this.router.navigate(["/main-screen/inbox"]);
      },
      error(err) {
        alert(err.error)
      }
    });
  }

}
