import { create } from "zustand";
import { subscribeUnsubscribeEvent } from "../services/join-event.service";
import { User } from "../model/User";
interface EventStore {
    joinEvent: (userId: string, eventId: string) => Promise<User>;
}

const useEventStore = create<EventStore>((set, get) => ({
    joinEvent: async (email: string, eventId: string): Promise<User> => {
        return await subscribeUnsubscribeEvent(email, eventId);
    },
}));

export default useEventStore;
