import axios from 'axios';
import { API_BASE_URL } from '../configuration/config';
import { Village } from '../model/Village';
import { Festivity } from '../model/Festivity';
export const getVillages = async (id: string): Promise<Village[]> => {
    try {
        const response = await axios.get<Village[]>(`${API_BASE_URL}/provinces/${id}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching provinces:', error);
        throw error;
    }
};


export const getVillageFestivity = async (): Promise<Festivity[]> => {
    try {
        const response = await axios.get<Festivity[]>(`${API_BASE_URL}/festivity`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching villages:', error);
        throw error;
    }
};
