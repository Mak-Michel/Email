export class Email {
    constructor(
        public id?: string,
        public emailBody?: string,
        public sender?: string,
        public receivers?: string[],
        public subject?: string,
        public date?: number,
        public read?: boolean,
        public priority?: number
    ) { }
}