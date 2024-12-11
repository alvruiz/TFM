import { create } from 'zustand';
import { Province } from '../model/Province';
import { getProvinces } from '../services/get-provinces.service';
interface ProvinceStore {
    provinces: Province[];
    isLoading: boolean;
    error: string | null;
    getProvinces: () => Promise<void>;
}

const useProvinceStore = create<ProvinceStore>((set) => ({
    provinces: [],
    isLoading: false,
    error: null,

    getProvinces: async () => {
        set({ isLoading: true, error: null });

        try {
            const response = await getProvinces();
            set({ provinces: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener las provincias', isLoading: false });
        }
    },
}));

export default useProvinceStore;