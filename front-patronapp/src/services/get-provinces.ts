import axios from 'axios';
import { Province } from '../model/Province';

export const getProvinces = async (): Promise<Province[]> => {
    try {
        const response = await axios.get<Province[]>('http://localhost:8080/provinces/', {
            headers: {
                'Accept': 'application/json',  // Especifica el tipo de respuesta que esperas
                'Content-Type': 'application/json'  // Si el servidor requiere que la petición esté en JSON
            },
            withCredentials: false  // Se asegura de que no se envíen cookies por defecto
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching provinces:', error);
        throw error;
    }
};
