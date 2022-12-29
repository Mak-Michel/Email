import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Controller/Classes/user';
import { HttpService } from 'src/app/Controller/Http/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private http: HttpService) { }

  private email: string = "";
  private password: string = "";
  private user: User;

  getEmail(email : any)
  {
    this.email = email.target.value;
  }
  getPassword(password : any)
  {
    this.password = password.target.value;
  }

  logIn() {
    this.http.getRequest(`user/signIn?userEmail=${this.email}&pass=${this.password}`).
    subscribe({
        next: (data) => this.router.navigate(["/main-screen/inbox"]),
        error(err) {
          alert(err.error)
        }
      }
    );
  }

}
