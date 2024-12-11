import axios from 'axios';
import { Province } from '../model/Province';
import { API_BASE_URL } from '../configuration/config';

export const getProvinces = async (): Promise<Province[]> => {
    try {
        const response = await axios.get<Province[]>(`${API_BASE_URL}/provinces`, {
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
