import React, { useEffect } from 'react';
import { Card, CardContent, StyledEngineProvider } from '@mui/material';
import Header from '../header/Header';
import { EditProfileContainer, Title, StyledButton, StyledTextField, StyledTypographyTitle, StyledTypographySubtitle } from './EditProfileStyles';
import ProfileCircle from '../header/ProfileCircleHeader';
import colors from '../../utils/colors';
import useUserStore from '../../stores/user-store';
import useVillageStore from '../../stores/village-store';
import { Role } from '../../model/Role';

const EditProfile = () => {
    const { user, setUser, getPersistedUser } = useUserStore();
    const { getVillage, village } = useVillageStore();
    useEffect(() => {
        const fetchedUser = getPersistedUser();
        if (fetchedUser.villageId) {
            getVillage(fetchedUser.villageId);
        }
        setUser(fetchedUser);
    }, []);




    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <StyledEngineProvider injectFirst>
            <div style={{ minHeight: '100vh', width: '100%', backgroundColor: colors.backgroundLight2 }}>
                <Header />

                <EditProfileContainer sx={{ backgroundColor: colors.backgroundLight2, flex: 1 }}>
                    <Title>Editar perfil</Title>

                    <div style={{ display: 'flex', justifyContent: 'center', marginBottom: '20px' }}>
                        <ProfileCircle imageUrl={user.imageUrl} altText={user.name} width={200} height={200} />
                    </div>
                    <StyledTextField label="Name" variant="outlined" defaultValue={user.name} />
                    <StyledTextField label="Email" variant="outlined" defaultValue={user.email} />
                    <StyledTextField label="Password" variant="outlined" type="password" defaultValue={user.password} />
                    {user.rol === Role.CM && (
                        <>
                            <StyledTypographyTitle>Gestor de: </StyledTypographyTitle>
                            <Card style={{ border: "none", boxShadow: "none", padding: 0, width: '100%' }}>
                                <CardContent sx={{ backgroundImage: `url(${village?.imageUrl ?? ""})`, backgroundSize: "cover", backgroundPosition: "center" }}>
                                    <div style={{ width: "100%", backgroundColor: colors.backgroundLight, padding: 10 }}>
                                        <StyledTypographyTitle>
                                            {village?.name ?? ""} - {village?.festivity?.name ?? ""}
                                        </StyledTypographyTitle>
                                        <StyledTypographySubtitle variant="body2" color="text.secondary">
                                        </StyledTypographySubtitle>
                                        <StyledTypographySubtitle variant="body2" color="text.secondary">
                                            Fecha de inicio: {village?.festivity?.startDate ?? ""} - Fecha de fin: {village?.festivity?.endDate ?? ""}
                                        </StyledTypographySubtitle>
                                    </div>
                                </CardContent>
                            </Card>
                        </>
                    )}
                    <StyledButton>Save Changes</StyledButton>

                </EditProfileContainer>
            </div>
        </StyledEngineProvider>
    );
};

export default EditProfile;
