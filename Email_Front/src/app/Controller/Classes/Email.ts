export class Email {
    constructor(
        public id?: string,
        public emailBody?: string,
        public sender?: string,
        public receivers?: string[],
        public subject?: string,
        public date?: string,
        public read?: boolean,
        public priority?: number,
        public attachments_IDS?: string[],
        public numberOfReceivers?: number,
        public numberOfAttachments?: number
    ) { }
}