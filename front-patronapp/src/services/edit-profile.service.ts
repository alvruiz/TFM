import { toast } from 'react-toastify';  // Importa el toast
import axios from "axios";
import { API_BASE_URL } from "../configuration/config";
import { User } from '../model/User';

export const updateUser = async (user: User): Promise<User> => {
    try {
        const response = await axios.put(`${API_BASE_URL}/user`,
            user
            , {
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
            toast.error("Error al actualizar perfil");
        } else {
            toast.error('Ha ocurrido un error inesperado. Error: ' + error.message);
        }

        throw error;
    }
};
