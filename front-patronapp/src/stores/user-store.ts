import { create } from 'zustand';
import { User } from '../model/User';
import { login } from '../services/login.service';
interface UserStore {
    user: User | null;
    isLoading: boolean;
    error: string | null;
    getUser: (email: String, password: String) => Promise<void>;
}

const useUserStore = create<UserStore>((set) => ({
    user: null,
    isLoading: false,
    error: null,

    getUser: async (email: string, password: string): Promise<void> => {
        set({ isLoading: true, error: null });

        try {
            const response = await login(email, password);
            set({ user: response, isLoading: false });
        } catch (error) {
            set({ error: 'Error al obtener el usuario', isLoading: false });
        }
    },
}));

export default useUserStore;