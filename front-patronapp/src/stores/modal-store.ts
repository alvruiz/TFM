import { create } from "zustand";
import FestivityEvent from "../model/Event";
interface ModalStore {
    openModal: boolean;
    selectedEvent: FestivityEvent | null;
    setOpenModal: (open: boolean) => void;
    setSelectedEvent: (event: any) => void;
}

const useModalStore = create<ModalStore>((set) => ({
    openModal: false,
    selectedEvent: null,
    setOpenModal: (open) => set({ openModal: open }),
    setSelectedEvent: (event) => set({ selectedEvent: event }),
}));

export default useModalStore;