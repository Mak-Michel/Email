import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpService } from './Controller/Http/http.service';
import { MainScreenModule } from './main-screen/main-screen.module';
import { RegisterationModule } from './registeration/registeration.module';
import { ProxyService } from './Controller/Proxy/proxy.service';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MainScreenModule,
    RegisterationModule,
    HttpClientModule       
  ],
  providers: [HttpService, ProxyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
