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
    actualVillages: Village[];
    festivities: Festivity[];
    isLoading: boolean;
    error: string | null;
    actualPage: number;
    amountItems: number;
    getProvinces: () => Promise<void>;
    getVillages: (id: string) => Promise<Village[]>;
    getVillagesPaginated: (id: string, page: number, itemsPerPage: number) => Promise<Village[]>;
    getFestivities: (page: number, itemsPerPage: number) => Promise<void>;
    setActualVillages: (actualVillages: Village[]) => Promise<void>
    setActualPage: (actualPage: number) => Promise<void>,
    setAmountItems: (amountItems: number) => Promise<void>
    resetPageAndAmountItems: () => Promise<void>
}

const useProvinceStore = create<ProvinceStore>((set, get) => ({
    provinces: [],
    actualPage: 0,
    amountItems: 9,
    actualProvince: null,
    actualVillages: [],
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
    getVillagesPaginated: async (id: string, page: number, itemsPerPage: number): Promise<Village[]> => {
        const actualProvince = get().provinces.find((province) => province.id === id);
        try {
            const response = await getVillages(id, page, itemsPerPage);


            set({ actualProvince: actualProvince, villages: response, isLoading: false });
            return response;
        } catch (error) {
            set({ error: 'Error al obtener las villages', isLoading: false });
        }
    },

    getVillages: async (id: string): Promise<Village[]> => {
        const actualProvince = get().provinces.find((province) => province.id === id);
        try {
            const response = await getVillages(id);

            set({ actualVillages: response, actualProvince: actualProvince, villages: response, isLoading: false });
            return response;
        } catch (error) {
            set({ error: 'Error al obtener las villages', isLoading: false });
        }
    },

    getFestivities: async (page: number, itemsPerPage: number) => {
        set({ isLoading: true, error: null });

        try {
            const response = await getVillageFestivity(page, itemsPerPage);
            set({ festivities: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener la festividad', isLoading: false });
        }
    },
    setActualVillages: async (villages) => {
        set({ actualVillages: villages })
    },
    setActualPage: async (page) => {
        set({ actualPage: page })
    },
    setAmountItems: async (amountItems) => {
        set({ amountItems: amountItems })
    },
    resetPageAndAmountItems: async () => {
        set({ actualPage: 0, amountItems: 9 })
    }
}));

export default useProvinceStore;