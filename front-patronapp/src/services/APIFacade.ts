import { User } from "../model/User";
import { getEvents } from "./get-events.service";
import { getEventsByEmail, getEventsByIds } from "./get-events.service";
import { getProvinces } from "./get-provinces.service";
import { getVillages, getVillage, getVillageFestivity } from "./get-villages.service";
import { subscribeUnsubscribeEvent } from "./join-event.service";
import { updateUser } from "./edit-profile.service";
import { login } from "./login.service";
import { register } from "./register.service";
class APIFacade {
    static async getEvents(festivityId: string) {
        try {
            return await getEvents(festivityId);
        } catch (error) {
            console.error("Error fetching events for festivity:", error);
            throw error;
        }
    }
    static async getEventsByFestivity(festivityId: string) {
        try {
            return await getEvents(festivityId);
        } catch (error) {
            console.error("Error fetching events for festivity:", error);
            throw error;
        }
    }

    static async getEventsByEmail(email: string) {
        try {
            return await getEventsByEmail(email);
        } catch (error) {
            console.error("Error fetching events for email:", error);
            throw error;
        }
    }

    static async getEventsByIds(ids: string[]) {
        try {
            return await getEventsByIds(ids);
        } catch (error) {
            console.error("Error fetching events by ids:", error);
            throw error;
        }
    }

    static async getProvinces() {
        try {
            return await getProvinces();
        } catch (error) {
            console.error("Error fetching provinces:", error);
            throw error;
        }
    }

    static async getVillages(id: string, page?: number, itemsPerPage?: number) {
        try {
            return await getVillages(id, page, itemsPerPage);
        } catch (error) {
            console.error("Error fetching villages:", error);
            throw error;
        }
    }

    static async getVillage(id: string) {
        try {
            return await getVillage(id);
        } catch (error) {
            console.error("Error fetching village:", error);
            throw error;
        }
    }

    static async getVillageFestivity(page: number, size: number) {
        try {
            return await getVillageFestivity(page, size);
        } catch (error) {
            console.error("Error fetching village festivity:", error);
            throw error;
        }
    }

    static async subscribeUnsubscribeEvent(email: string, eventId: string) {
        try {
            return await subscribeUnsubscribeEvent(email, eventId);
        } catch (error) {
            console.error("Error subscribing or unsubscribing to event:", error);
            throw error;
        }
    }

    static async login(email: string, password: string) {
        try {
            return await login(email, password);
        } catch (error) {
            console.error("Error logging in:", error);
            throw error;
        }
    }

    static async register(name: string, surname: string, email: string, password: string, age: string, gender: string, imageUrl: string) {
        try {
            return await register(name, surname, email, password, age, gender, imageUrl);
        } catch (error) {
            console.error("Error registering:", error);
            throw error;
        }
    }

    static async updateUser(user: User) {
        try {
            console.log(user);
            return await updateUser(user);
        } catch (error) {
            console.log("Error updating user");
            return;
        }
    }
}

export default APIFacade;
