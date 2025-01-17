import { create } from 'zustand';
import { User } from '../model/User';
import FestivityEvent from '../model/Event';
import APIFacade from '../services/APIFacade';
import { authenticate } from '../services/login.service';
import { Role } from '../model/Role';
interface UserStore {
    user: User | null;
    isLoading: boolean;
    error: string | null;
    jwt: string | null;
    eventsUser: FestivityEvent[];
    getUser: (email: string, password: string) => Promise<User>;
    updateUser: (user: User) => void;
    authenticate: (email: string, password: string) => Promise<string>;
    getEventsUser: (email: string) => Promise<void>;
    getPersistedUser: () => User | null;
    getPersistedJwt: () => string | null;
    setUser(user: User): void;
    logOut: () => void;
}

const useUserStore = create<UserStore>((set) => {
    const localStorageUser = localStorage.getItem('user');
    return {
        eventsUser: [],
        jwt: '',
        user: localStorageUser ? JSON.parse(localStorageUser) : null,
        isLoading: false,
        error: null,
        setUser: (user: User) => {
            set({ user });
            localStorage.setItem('user', JSON.stringify(user));
        },
        authenticate: async (email: string, password: string): Promise<string> => {
            set({ isLoading: true, error: null });
            try {
                const response = await APIFacade.authenticate(email, password);

                return response;
            } catch (error) {
                set({ error: 'Error al obtener el usuario', isLoading: false });
            }
        },
        getUser: async (email: string, password: string): Promise<User> => {
            set({ isLoading: true, error: null });

            try {
                const response = await APIFacade.login(email, password);
                let jwt = ''
                if (response.rol === Role.CM || response.rol === Role.ADMIN) {
                    jwt = await authenticate(email, password);

                }
                set({ user: response, isLoading: false, jwt });
                localStorage.setItem('user', JSON.stringify(response));
                localStorage.setItem('jwt', jwt);

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
        getPersistedJwt: () => {
            const savedJwt = localStorage.getItem('jwt');

            set({ jwt: savedJwt });
            return savedJwt;
        },
        getEventsUser: async (email: string) => {
            set({ isLoading: true, error: null });

            try {
                const response = await APIFacade.getEventsByEmail(email);
                set({ eventsUser: response, isLoading: false });
            } catch (error) {
                set({ error: 'Error al obtener los eventos', isLoading: false });
            }
        },
        logOut: () => {
            set({ user: null });
            localStorage.removeItem('user'); // Elimina el usuario del localStorage
        },
        updateUser: async (user: User) => {
            const response = await APIFacade.updateUser(user).then().catch(e => { throw Error("Error updating user") });
            set({ user });
            localStorage.setItem('user', JSON.stringify(user));
        },
    };
});

export default useUserStore;
