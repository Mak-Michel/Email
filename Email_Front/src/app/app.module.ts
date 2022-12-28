import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainScreenModule } from './main-screen/main-screen.module';
import { RegisterationModule } from './registeration/registeration.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MainScreenModule,
    RegisterationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
