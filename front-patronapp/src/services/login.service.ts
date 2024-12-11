import { toast } from 'react-toastify';  // Importa el toast
import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import { User } from '../model/User';

export const login = async (email: string, password: string): Promise<User> => {
    try {
        const response = await axios.post(`${API_BASE_URL}/user/login`, {
            email,
            password
        }, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            withCredentials: false
        });
        return response.data;
    } catch (error: any) {
        console.error('Error logging in:', error);

        // Si el error tiene una respuesta del servidor y es un 404
        if (error.response && error.response.status === 404) {
            toast.error("Error al iniciar sesión: Credenciales incorrectas.");
        } else {
            toast.error('Ha ocurrido un error inesperado. Error: ' + error.message);
        }

        throw error;
    }
};
