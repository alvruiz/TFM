import { create } from 'zustand';
import { Province } from '../model/Province';
import { getProvinces } from '../services/get-provinces.service';
import { getVillageFestivity, getVillages } from '../services/get-villages.service';
import { Village } from '../model/Village';
import { Festivity } from '../model/Festivity';
interface ProvinceStore {
    provinces: Province[];
    villages: Village[];
    actualProvince: Province | null;
    festivities: Festivity[];
    isLoading: boolean;
    error: string | null;
    getProvinces: () => Promise<void>;
    getVillages: (id: string) => Promise<void>;
    getFestivities: () => Promise<void>;
}

const useProvinceStore = create<ProvinceStore>((set, get) => ({
    provinces: [],
    actualProvince: null,
    festivities: [],
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
        const actualProvince = get().provinces.find((province) => province.id === id);
        console.log(actualProvince);
        try {
            const response = await getVillages(id);
            set({ actualProvince: actualProvince, villages: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener las villages', isLoading: false });
        }
    },

    getFestivities: async () => {
        set({ isLoading: true, error: null });

        try {
            const response = await getVillageFestivity();
            set({ festivities: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener la festividad', isLoading: false });
        }
    },
}));

export default useProvinceStore;