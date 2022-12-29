export class EmailHeader {
    constructor(
        public id?: string,
        public headerBody?: string,
        public sender?: string,
        public receiver?: string[],
        public subject?: string,
        public date?: number,
        public read?: boolean ){}
}