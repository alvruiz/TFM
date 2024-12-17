export default interface FestivityEvent {
    id: string,
    eventName: string;
    eventDescription: string;
    eventStartDate: Date;
    eventEndDate: Date;
    coords: {
        latitude: string;
        longitude: string;
    }[];
    attendees: string[];
    eventMaxCapacity: number;
    eventFestivityId: string;
}