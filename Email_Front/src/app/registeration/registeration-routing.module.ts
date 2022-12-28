import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './r-components/login/login.component';
import { SignupComponent } from './r-components/signup/signup.component';
import { RegisterationComponent } from './registeration.component';


const routes: Routes = [
  {
     path: 'registeration' , component: RegisterationComponent ,
     children:[
      { path: '' , redirectTo: '/registeration/signup' , pathMatch: 'full' },    // if path: '' redirect to signup
      { path: 'signup' , component: SignupComponent },
      { path: 'login' , component: LoginComponent }
    ]
}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterationRoutingModule { }
