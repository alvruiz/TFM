import axios from 'axios';
import { API_BASE_URL } from '../configuration/config';
import { Village } from '../model/Village';
import { Festivity } from '../model/Festivity';
export const getVillages = async (id: string, page?: number, itemsPerPage?: number): Promise<Village[]> => {
    try {
        if (!page && !itemsPerPage) {
            const response = await axios.get<Village[]>(`${API_BASE_URL}/provinces/${id}/villages`, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                withCredentials: false
            });
            return response.data;
        }
        const response = await axios.get<Village[]>(`${API_BASE_URL}/provinces/${id}/villages?page=${page}&size=${itemsPerPage}`, {
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


export const getVillageFestivity = async (page: number, size: number): Promise<Festivity[]> => {
    try {
        const response = await axios.get<Festivity[]>(`${API_BASE_URL}/festivities?page=${page}&size=${size}`, {
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


export const getVillage = async (id: string): Promise<Village> => {
    try {
        const response = await axios.get<Village>(`${API_BASE_URL}/villages/${id}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching village:', error);
        throw error;
    }
};