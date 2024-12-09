import axios from 'axios';

export const getProvinces = async () => {
    try {
        const response = await axios.get('/api/provinces'); // Ajusta la URL según tu API
        return response.data;
    } catch (error) {
        console.error('Error fetching provinces:', error);
        throw error;
    }
};