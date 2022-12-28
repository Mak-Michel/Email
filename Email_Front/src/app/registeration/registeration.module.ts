import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterationComponent } from './registeration.component';
import { SignupComponent } from './r-components/signup/signup.component';
import { LoginComponent } from './r-components/login/login.component';
import { RegisterationRoutingModule } from './registeration-routing.module';


@NgModule({
  declarations: [
    RegisterationComponent,
    SignupComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RegisterationRoutingModule
  ]
})
export class RegisterationModule { }
