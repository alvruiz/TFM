import { create } from "zustand";
import { User } from "../model/User";
import FestivityEvent from "../model/Event";
import APIFacade from "../services/APIFacade";
interface EventStore {
    userEvents: FestivityEvent[];
    setUserEvents: (userEvents: FestivityEvent[]) => void;
    getUserEvents: (ids: string[]) => void;
    joinEvent: (userId: string, eventId: string) => Promise<User>;
}

const useEventStore = create<EventStore>((set, get) => ({
    userEvents: [],
    setUserEvents: (userEvents: FestivityEvent[]) => set({ userEvents }),
    getUserEvents: async (ids: string[]) => {
        const userEvents = await APIFacade.getEventsByIds(ids);
        set({ userEvents });
    },

    joinEvent: async (email: string, eventId: string): Promise<User> => {
        return await APIFacade.subscribeUnsubscribeEvent(email, eventId);
    },
}));

export default useEventStore;
