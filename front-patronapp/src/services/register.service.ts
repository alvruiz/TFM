import { toast } from 'react-toastify';  // Importa el toast
import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import { User } from '../model/User';

export const register = async (name: string, surname: string, email: string, password: string, age: string, gender: string, imageUrl: string): Promise<User> => {
    try {
        const response = await axios.post(`${API_BASE_URL}/user`, {
            name,
            surname,
            age,
            gender,
            email,
            password,
            imageUrl
        }, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        toast.success('Registro exitoso');
        return response.data;
    } catch (error: any) {
        console.error('Error logging in:', error);

        // Si el error tiene una respuesta del servidor y es un 500
        if (error.response && error.response.status === 500) {
            toast.error("Error al registrarse");
            return
        } else {
            toast.error('Ha ocurrido un error inesperado. Error: ' + error.message);
        }

        throw error;
    }
};
