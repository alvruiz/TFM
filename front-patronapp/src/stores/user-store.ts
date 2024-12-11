import { create } from 'zustand';
import { User } from '../model/User';
import { login } from '../services/login.service';

interface UserStore {
    user: User | null;
    isLoading: boolean;
    error: string | null;
    getUser: (email: string, password: string) => Promise<User>;
    logOut: () => void;
}

const useUserStore = create<UserStore>((set) => {
    const savedUser = localStorage.getItem('user');
    const parsedUser = savedUser ? JSON.parse(savedUser) : null;

    return {
        user: parsedUser,
        isLoading: false,
        error: null,

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

        logOut: () => {
            set({ user: null });
            localStorage.removeItem('user'); // Elimina el usuario del localStorage
        },
    };
});

export default useUserStore;
