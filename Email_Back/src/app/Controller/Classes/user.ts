export class User {

    private name: string = "";
    private id: string = "";
    private userPassword: string = "";
    private sentEmailsIds: string[] = [""]; 
    private receivedEmailsIds: string[] = [""]; 
    private trashEmailsIds: string[] = [""]; 
    private draftEmailsIds: string[] = [""]; 
    private contacts: Map<string, string>;

    constructor(
        name?: string,
        id?: string,
        userPassword?: string,
        sentEmailsIds?: string[],
        receivedEmailsIds?: string[],
        trashEmailsIds?: string[],
        draftEmailsIds?: string[],
        contacts?: Map<string, string>
    ) {
        this.name = name;
        this.id = id;
        this.userPassword = userPassword;
        this.sentEmailsIds = sentEmailsIds;
        this.receivedEmailsIds = receivedEmailsIds;
        this.trashEmailsIds = trashEmailsIds;
        this.draftEmailsIds = draftEmailsIds;
        this.contacts = contacts;
    } 

}
