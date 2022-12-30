import { Injectable } from '@angular/core';
import { Email } from '../Classes/Email';
import { User } from '../Classes/User';
import { HttpService } from '../Http/http.service';

@Injectable({
  providedIn: 'root'
})
export class ProxyService {

  constructor(private http: HttpService) { }
  
  private currentUser: string = "";

  public setUser(user: string){
    this.currentUser = user;
  }

  public getUser(){
    return this.currentUser;
  }

  public signIn(userEmail: string, password: string){
    return this.http.getRequest(`user/signIn?userEmail=${userEmail}&pass=${password}`)
  }

  public signUp(name: string, userEmail: string, password: string){
    let newUser: User = new User(name, userEmail, password);
    return this.http.postRequest("user/signUp", newUser)
  }

  public getEmailList(emailType: string){
    console.log(`email/list?userEmail=${this.currentUser}&listType=${emailType}`);
    if(emailType != "inbox" && emailType != "sent" &&emailType != "draft" &&emailType != "trashed")
      return this.http.getRequest(`email/list?userEmail=inbox&listType=${emailType}`)
    return this.http.getRequest(`email/list?userEmail=${this.currentUser}&listType=${emailType}`)
  }

  public createNewEmail(email: Email){
    return this.http.postRequest("email/new", email);
  }

  public editEmail(email: Email){
    return this.http.postRequest("email/edit", email);
  }

  public deleteEmail(userEmail: string, emailId: string){
    return this.http.deleteRequest(`email/delete?emailId=${emailId}&userEmail=${this.currentUser}`);
  }

  public trashEmail(emailId: string){
    return this.http.putRequest(`email/trash?emailId=${emailId}&userEmail=${this.currentUser}`);
  }

  public addAttachment(){
    return this.http.getRequest("attachments/open");
  }

  public searchEmails(emailType: string, query: string, searchType: string){
    return this.http.getRequest("");
  }

  public searchContacts(query: string){
    return this.http.getRequest("");
  }

  public sortEmails(emailType: string, sortType: string){
    return this.http.getRequest("");
  }

  public signOut(){
    return this.http.putRequest(`user/signOut?userEmail=${this.currentUser}`)
  }

  public getEmail(emailId: string){
    return this.http.getRequest(`email/open?emailId=${emailId}`)
  }

  public sortContacts(){
    return this.http.getRequest("");
  }
}