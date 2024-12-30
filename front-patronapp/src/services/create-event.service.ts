import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import FestivityEvent from "../model/Event";


export const createEvent = async (event: FestivityEvent, festivityId: string, token: string): Promise<FestivityEvent> => {
    try {
        console.log(`Bearer ${token}`);

        const response = await axios.post<FestivityEvent>(`${API_BASE_URL}/events`, { ...event, eventFestivityId: festivityId, attendees: [] }, {
            headers: { 'Authorization': 'Bearer ' + token, Accept: 'application/json', 'Content-Type': 'application/json' },
            withCredentials: false
        });


        return response.data;
    } catch (error) {
        console.error('Error fetching events:', error);
        throw error;
    }
};

