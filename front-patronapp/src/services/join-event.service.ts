import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import FestivityEvent from "../model/Event";


export const subscribeUnsubscribeEvent = async (email: string, eventId: string): Promise<any> => {
    try {
        const response = await axios.post<any>(`${API_BASE_URL}/events/subscribe`, {
            email,
            eventId
        }, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching events:', error);
        throw error;
    }
};