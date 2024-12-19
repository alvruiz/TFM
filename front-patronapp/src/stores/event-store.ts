import { create } from "zustand";
import { subscribeUnsubscribeEvent } from "../services/join-event.service";
import { User } from "../model/User";
import FestivityEvent from "../model/Event";
import { getEventsByEmail, getEventsByIds } from "../services/get-events.service";
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
        const userEvents = await getEventsByIds(ids);
        set({ userEvents });
    },

    joinEvent: async (email: string, eventId: string): Promise<User> => {
        return await subscribeUnsubscribeEvent(email, eventId);
    },
}));

export default useEventStore;
