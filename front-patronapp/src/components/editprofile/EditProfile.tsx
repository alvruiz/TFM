import React, { useEffect, useState } from 'react';
import { StyledEngineProvider } from '@mui/material';
import Header from '../header/Header';
import { EditProfileContainer, Title, StyledButton, StyledTextField, ProfileImageContainer } from './EditProfileStyles';
import ProfileCircle from '../header/ProfileCircleHeader';
import colors from '../../utils/colors';

const EditProfile = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchedUser = getUserFromLocalStorage(); // Obtener el usuario desde localStorage
        setUser(fetchedUser);
    }, []);


    const getUserFromLocalStorage = () => {
        const user = localStorage.getItem('user');
        return user ? JSON.parse(user) : null;
    };

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <StyledEngineProvider injectFirst>
            <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh', width: '100%' }}>
                <Header />

                <EditProfileContainer sx={{ backgroundColor: colors.backgroundLight2, flex: 1 }}>
                    <Title>Edit Profile</Title>
                    <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
                        <ProfileCircle imageUrl={user.imageUrl} altText={user.name} width={200} height={200} />
                    </div>
                    <StyledTextField label="Name" variant="outlined" defaultValue={user.name} />
                    <StyledTextField label="Email" variant="outlined" defaultValue={user.email} />
                    <StyledTextField label="Password" variant="outlined" type="password" defaultValue={user.password} />
                    <StyledButton>Save Changes</StyledButton>
                </EditProfileContainer>
            </div>
        </StyledEngineProvider>
    );
};

export default EditProfile;
