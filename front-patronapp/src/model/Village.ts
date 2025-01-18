import { Festivity } from "./Festivity";

export interface Village {
    id: string;
    coords: {
        longitude: string,
        latitude: string
    }
    imageUrl: string,
    name: string,
    provinceId: string,
    festivity: Festivity
}
