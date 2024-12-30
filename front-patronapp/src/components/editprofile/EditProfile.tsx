import React, { useEffect, useState } from 'react';
import { Card, CardContent, StyledEngineProvider } from '@mui/material';
import Header from '../header/Header';
import { EditProfileContainer, Title, StyledButton, StyledTextField, StyledTypographyTitle, StyledTypographySubtitle } from './EditProfileStyles';
import ProfileCircle from '../header/ProfileCircleHeader';
import colors from '../../utils/colors';
import useUserStore from '../../stores/user-store';
import useVillageStore from '../../stores/village-store';
import { Role } from '../../model/Role';
import { toast } from 'react-toastify'
const EditarPerfil = () => {
    const { user, setUser, getPersistedUser, updateUser, getUser } = useUserStore();
    const { getVillage, village } = useVillageStore();
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        nuevaContrasena: ""
    });

    useEffect(() => {
        const fetchedUser = getPersistedUser();
        if (fetchedUser.villageId) {
            getVillage(fetchedUser.villageId);
        }
        setUser(fetchedUser);
        setFormData({
            name: fetchedUser.name,
            email: fetchedUser.email,
            password: "",
            nuevaContrasena: ""
        });
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        if (!formData.name || !formData.email || !formData.password) {
            alert("Por favor, completa todos los campos obligatorios.");
            return;
        }

        const updatedUser = {
            ...user,
            name: formData.name,
            email: formData.email,
            password: formData.nuevaContrasena || formData.password
        };
        await getUser(formData.email, formData.password)

        try {

            updateUser(updatedUser);
            toast.success("Updated user")
            setFormData((prevData) => ({
                ...prevData,
                password: "",
                nuevaContrasena: ""
            }));
        } catch (error) {
            toast.error("Failed to update user")
            console.error("Error al actualizar el perfil:", error.message);
        }
    };

    if (!user) {
        return <div>Cargando...</div>;
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
                    <StyledTextField
                        label="Nombre"
                        variant="outlined"
                        name="name"
                        value={formData.name}
                        onChange={handleInputChange}
                        required
                    />
                    <StyledTextField
                        label="Correo Electrónico"
                        variant="outlined"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange}
                        required
                    />
                    <StyledTextField
                        label="Contraseña"
                        variant="outlined"
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        required
                    />
                    <StyledTextField
                        label="Nueva Contraseña"
                        variant="outlined"
                        type="password"
                        name="nuevaContrasena"
                        value={formData.nuevaContrasena}
                        onChange={handleInputChange}
                    />

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
                    <StyledButton onClick={handleSubmit}>Guardar Cambios</StyledButton>

                </EditProfileContainer>
            </div>
        </StyledEngineProvider>
    );
};

export default EditarPerfil;
