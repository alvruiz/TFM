import { Role } from "./Role";
export interface User {
    email: string;
    password: string;
    name: string;
    surname: string;
    age: string;
    gender: string;
    rol: Role;
    imageUrl: string;
    eventsParticipating: string[];
    villageId: string;
}
