import { create } from "zustand";
import { getEvents } from "../services/get-events.service";
import { getVillage } from "../services/get-villages.service";
import { Village } from "../model/Village";
import { Event } from "../model/Event";

interface VillageStore {
    village: Village | null;
    events: Event[];
    isLoading: boolean;
    error: string | null;
    getVillage: (id: string) => Promise<void>;
    getEvents: (festivityId: string) => Promise<void>;
}

const useVillageStore = create<VillageStore>((set, get) => ({
    village: null,
    events: [],
    isLoading: false,
    error: null,

    getVillage: async (id: string) => {
        set({ isLoading: true, error: null });
        try {
            const response = await getVillage(id);
            set({ village: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener la villa', isLoading: false });
        }
    },
    getEvents: async (festivityId: string) => {
        set({ isLoading: true, error: null });
        try {
            const response = await getEvents(festivityId);
            set({ events: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener los eventos', isLoading: false });
        }
    },
}));

export default useVillageStore;