import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import { Event } from "../model/Event";


export const getEvents = async (festivityId: string): Promise<Event[]> => {
    try {
        const response = await axios.get<Event[]>(`${API_BASE_URL}/events/${festivityId}`, {
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