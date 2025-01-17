import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import FestivityEvent from "../model/Event";
export const deleteEvent = async (id: string, token): Promise<FestivityEvent> => {
    try {
        const response = await axios.delete<FestivityEvent>(`${API_BASE_URL}/events/${id}`, {
            headers: { 'Authorization': 'Bearer ' + token, Accept: 'application/json', 'Content-Type': 'application/json' },
            withCredentials: false
        });


        return response.data;
    } catch (error) {
        console.error('Error fetching events:', error);
        throw error;
    }
};

