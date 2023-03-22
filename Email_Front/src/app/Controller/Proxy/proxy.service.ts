import { Injectable } from '@angular/core';
import { Email } from '../Classes/Email';
import { User } from '../Classes/user';
import { HttpService } from '../Http/http.service';
import { Router } from '@angular/router';
import { Contact } from '../Classes/Contact';

@Injectable({
  providedIn: 'root'
})
export class ProxyService {

  public currentUser: string = "";

  public currentFolders: string[] = []

  constructor(router: Router, private http: HttpService) {
    if(this.currentUser == "") {
      router.navigate(["/registeration/login"]);
      return;
    }
  }

  public setUser(user: string){
    this.currentUser = user;
  }

  public getUser(){
    return this.currentUser;
  }

  public setFolders(folders: string[]){
    this.currentFolders = folders;
  }

  public getFolders(): string[]{
    return this.currentFolders;
  }

  public signIn(userEmail: string, password: string){
    return this.http.getRequest(`user/signIn?userEmail=${userEmail}&pass=${password}`)
  }

  public signUp(name: string, userEmail: string, password: string){
    let newUser: User = new User(name, userEmail, password);
    return this.http.postRequest("user/signUp", newUser)
  }

  public getEmailList(emailType: string){
    return this.http.getRequest(`email/list?userEmail=${this.currentUser}&listType=${emailType}`)
  }

  public createNewEmail(newEmail: Email){
    alert(newEmail)
    return this.http.postRequest("email/new", newEmail);
  }
  public editEmail(email: Email){
    return this.http.postRequest("email/edit", email); 
  }

  public deleteEmail(emailId: string){
    return this.http.deleteRequest(`email/deleteFromUser?userEmail=${this.currentUser}&emailId=${emailId}`);
  }

  public trashEmail(emailId: string){
    return this.http.putRequest("email/trash", [emailId, this.currentUser]);
  }

  public addAttachment(){
    return this.http.getRequest("attachments/open");
  }

  public searchEmails(emailType: string, searchType: string, searchKey: string){
    return this.http.getRequest(`email/search?userEmail=${this.currentUser}&emailType=${emailType}&searchType=${searchType}&searchKey=${searchKey}`);
  }

  public filterEmails(emailType: string, filterType: string, compared: string, bool: string){
    console.log(emailType + " " + filterType + " " + compared + " " + bool)
    return this.http.getRequest(`email/filter?userEmail=${this.currentUser}&emailType=${emailType}&filterType=${filterType}&compared=${compared}&bool=${bool}`);
  }

  public searchContacts(query: string){
    return this.http.getRequest("");
  }

  public sortEmails(emailType: string, sortType: string){
    return this.http.getRequest(`email/sort?userEmail=${this.currentUser}&emailType=${emailType}&sortType=${sortType}`);
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

  public addContact(contact: Contact){
    return this.http.postRequest(`user/addContact?userEmail=${this.currentUser}`, contact);
  }

  public editContact(contact: Contact){
    return this.http.putRequest(`user/editContact?userEmail=${this.currentUser}`, contact);
  }

  public getContact(contactName: string){
    return this.http.getRequest(`user/contact?userEmail=${this.currentUser}&name=${contactName}`);
  }

  public getContactList(){
    return this.http.getRequest(`user/contacts?userEmail=${this.currentUser}`);
  }

  public deleteContact(contactName: string){
    return this.http.deleteRequest(`user/deleteContact?userEmail=${this.currentUser}&contactName=${contactName}`);
  }

  public getFolderList(){
    return this.http.getRequest(`user/folderList?userEmail=${this.currentUser}`);
  }

  public createNewFolder(folderName: string){
    return this.http.postRequest(`user/newFolder?userEmail=${this.currentUser}&name=${folderName}`);
  }

  public moveEmails(source: string, destination: string, emailId: string){
    return this.http.putRequest(`user/moveEmail?userEmail=${this.currentUser}&source=${source}&destination=${destination}&emailId=${emailId}`)
  }

  public restoreEmail(emailId: string){
    return this.http.putRequest(`email/restoreEmail?userEmail=${this.currentUser}&emailId=${emailId}`)
  }

  public createDraft(emailParameters: string[]){ 
    return this.http.postRequest(`email/draft`, emailParameters)
  }

  public deleteFolder(folderName: string){
    return this.http.deleteRequest(`user/deleteFolder?userEmail=${this.currentUser}&name=${folderName}`);
  }

  public upload(formData: FormData) {
    return this.http.postRequest("Attachments/upload", formData);
  }

  public download(name: string) {
    return this.http.download(name);
  }
}