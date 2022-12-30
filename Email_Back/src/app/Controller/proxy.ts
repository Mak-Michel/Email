import { Email } from "./Classes/Email";
import { User } from "./Classes/user";
import { HttpService } from "./Http/http.service";

export class proxy{

    constructor(private http: HttpService){}

    private currentUser: string;

    public setUser(user:  string){
        this.currentUser = user;
    }

    public signIn(userEmail: string, password: string){
        return this.http.getRequest(`user/signIn?userEmail=${userEmail}&pass=${password}`)
    }

    public signUp(name: string, userEmail: string, password: string){
        let newUser: User = new User(name, userEmail, password);
        return this.http.postRequest("user/signUp", newUser)
    }

    public getEmailList(emailType: string){
        if(emailType != "inbox" && emailType != "sent" &&emailType != "draft" &&emailType != "trash")
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
        return this.http.deleteRequest(`email/trash?emailId=${emailId}&userEmail=${this.currentUser}`);
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

    public sortContacts(){
        return this.http.getRequest("");
    }
}