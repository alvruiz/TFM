import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import FestivityEvent from "../model/Event";


export const getEvents = async (festivityId: string): Promise<FestivityEvent[]> => {
    try {
        const response = await axios.get<FestivityEvent[]>(`${API_BASE_URL}/events/${festivityId}`, {
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

export const getEventsByEmail = async (email: string): Promise<FestivityEvent[]> => {
    try {
        const response = await axios.get<FestivityEvent[]>(`${API_BASE_URL}/events/email/${email}`, {
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