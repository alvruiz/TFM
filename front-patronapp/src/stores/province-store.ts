import { create } from 'zustand';
import axios from 'axios';
import { Province } from '../model/Province';
interface ProvinciaStore {
    provincias: Province[];
    isLoading: boolean;
    error: string | null;
    getProvinces: () => Promise<void>;
}

const useProvinciaStore = create<ProvinciaStore>((set) => ({
    provincias: [],
    isLoading: false,
    error: null,

    getProvinces: async () => {
        set({ isLoading: true, error: null });

        try {
            const response = await axios.get<Province[]>('https://api.example.com/provincias'); // Reemplaza con tu API
            set({ provincias: response.data, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener las provincias', isLoading: false });
        }
    },
}));

export default useProvinciaStore;