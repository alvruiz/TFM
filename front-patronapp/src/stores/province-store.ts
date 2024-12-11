import { create } from 'zustand';
import { Province } from '../model/Province';
import { getProvinces } from '../services/get-provinces.service';
import { getVillages } from '../services/get-villages.service';
import { Village } from '../model/Village';
interface ProvinceStore {
    provinces: Province[];
    villages: Village[];
    isLoading: boolean;
    error: string | null;
    getProvinces: () => Promise<void>;
    getVillages: (id: string) => Promise<void>;
}

const useProvinceStore = create<ProvinceStore>((set) => ({
    provinces: [],
    villages: [],
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
    getVillages: async (id: string) => {
        set({ isLoading: true, error: null });

        try {
            const response = await getVillages(id);
            set({ villages: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener las villages', isLoading: false });
        }
    },
}));

export default useProvinceStore;