export class User {
    constructor(
        public name?: string,
        public id?: string,
        public userPassword?: string,
        public sentEmailsIds?: string[],
        public receivedEmailsIds?: string[],
        public trashEmailsIds?: string[],
        public draftEmailsIds?: string[],
        public contacts?: Map<string, string>
    ) { } 
}
