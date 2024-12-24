import { create } from "zustand";
import { Village } from "../model/Village";
import FestivityEvent from "../model/Event";
import APIFacade from "../services/APIFacade";
interface VillageStore {
    village: Village | null;
    events: FestivityEvent[];
    isLoading: boolean;
    error: string | null;
    getVillage: (id: string) => Promise<void>;
    setVillage: (village: Village) => void;
    setEvents: (events: FestivityEvent[]) => void;
    getEvents: (festivityId: string) => Promise<void>;
}

const useVillageStore = create<VillageStore>((set, get) => ({
    village: null,
    events: [],
    isLoading: false,
    error: null,

    setVillage: (village: Village) => set({ village }),
    setEvents: (events: FestivityEvent[]) => set({ events }),
    getVillage: async (id: string) => {
        set({ isLoading: true, error: null });
        try {
            const response = await APIFacade.getVillage(id);
            set({ village: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener la villa', isLoading: false });
        }
    },
    getEvents: async (festivityId: string) => {
        set({ isLoading: true, error: null });
        try {
            const response = await APIFacade.getEvents(festivityId);
            set({ events: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener los eventos', isLoading: false });
        }
    },
}));

export default useVillageStore;