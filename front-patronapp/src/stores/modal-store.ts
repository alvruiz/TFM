import { create } from "zustand";
import FestivityEvent from "../model/Event";
import { Village } from "../model/Village";
interface ModalStore {
    openModal: boolean;
    selectedEvent: FestivityEvent | null;
    selectedVillage: Village | null;
    setOpenModal: (open: boolean) => void;
    setSelectedEvent: (event: any) => void;
    setSelectedVillage: (village: any) => void;
    restore: () => void;
}

const useModalStore = create<ModalStore>((set) => ({
    openModal: false,
    selectedVillage: null,
    selectedEvent: null,
    setOpenModal: (open) => set({ openModal: open }),
    setSelectedEvent: (event) => set({ selectedEvent: event }),
    setSelectedVillage: (village) => set({ selectedVillage: village }),
    restore: () => set({ openModal: false, selectedEvent: null, selectedVillage: null })
}));

export default useModalStore;