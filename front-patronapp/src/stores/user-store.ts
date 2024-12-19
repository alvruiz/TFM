import { create } from 'zustand';
import { User } from '../model/User';
import { login } from '../services/login.service';
import { getEventsByEmail } from '../services/get-events.service';
import FestivityEvent from '../model/Event';

interface UserStore {
    user: User | null;
    isLoading: boolean;
    error: string | null;
    eventsUser: FestivityEvent[];
    getUser: (email: string, password: string) => Promise<User>;
    updateUser: (user: User) => void;
    getEventsUser: (email: string) => Promise<void>;
    getPersistedUser: () => User | null;
    setUser(user: User): void;
    logOut: () => void;
}

const useUserStore = create<UserStore>((set) => {
    return {
        eventsUser: [],
        user: null,
        isLoading: false,
        error: null,
        setUser: (user: User) => {
            set({ user });
            localStorage.setItem('user', JSON.stringify(user));
        },
        getUser: async (email: string, password: string): Promise<User> => {
            set({ isLoading: true, error: null });

            try {
                const response = await login(email, password);

                set({ user: response, isLoading: false });
                localStorage.setItem('user', JSON.stringify(response));

                return response;
            } catch (error) {
                set({ error: 'Error al obtener el usuario', isLoading: false });
            }
        },
        getPersistedUser: () => {
            const savedUser = localStorage.getItem('user');
            const parsedUser = savedUser ? JSON.parse(savedUser) : null;

            set({ user: parsedUser });
            return parsedUser;
        },
        getEventsUser: async (email: string) => {
            set({ isLoading: true, error: null });

            try {
                const response = await getEventsByEmail(email);
                set({ eventsUser: response, isLoading: false });
            } catch (error) {
                set({ error: 'Error al obtener los eventos', isLoading: false });
            }
        },
        logOut: () => {
            set({ user: null });
            localStorage.removeItem('user'); // Elimina el usuario del localStorage
        },
        updateUser: (user: User) => {
            set({ user });
            localStorage.setItem('user', JSON.stringify(user));
        },
    };
});

export default useUserStore;
