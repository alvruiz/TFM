export interface Province {
    id: string,
    name: string,
    coords: {
        latitude: string,
        longitude: string
    },
    image: string
    autonomousCommunity: string
}