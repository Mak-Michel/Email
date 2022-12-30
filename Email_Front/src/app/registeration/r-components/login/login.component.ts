import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private proxy: ProxyService) { }

  private email: string = "";
  private password: string = "";

  getEmail(email : any)
  {
    this.email = email.target.value;
  }
  getPassword(password : any)
  {
    this.password = password.target.value;
  }

  logIn() {
    this.proxy.signIn(this.email, this.password).
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
