import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Controller/Classes/user';
import { HttpService } from 'src/app/Controller/Http/http.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private router: Router, private http: HttpService) { }

  private name: string = "";
  private email: string = "";
  private password: string = "";
  private user: User;

  getName(name : any)
  {
    this.name = name.target.value;
  }
  getEmail(email : any)
  {
    this.email = email.target.value;
  }
  getPassword(password : any)
  {
    this.password = password.target.value;
  }

  signUp() {
    this.user = new User(this.name, this.email, this.password);
    this.http.postRequest("user/signUp", this.user).
    subscribe(
      (data) => {
        console.log(data)
        this.router.navigate(["/main-screen/inbox"]);
      }
    );
  }

}
