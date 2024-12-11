export interface Village {
    id: string;
    name: string;
    coords: {
        longitude: string,
        latitude: string
    }
    imageUrl: string,
    provinceId: string
}
