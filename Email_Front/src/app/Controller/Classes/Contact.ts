export class Contact {
    constructor(
        public name?: string,
        public userEmails?: string[],
        public selected: boolean = false 
    ) { }
}