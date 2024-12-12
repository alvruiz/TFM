import React, { useEffect } from "react";
import { Stack, Button, Toolbar, Container, AppBar, Avatar, IconButton } from "@mui/material";
import { Logout, Person } from '@mui/icons-material';
import colors from "../../utils/colors";
import { useNavigate } from "react-router-dom";
import useUserStore from "../../stores/user-store";

const Header = () => {
    const navigate = useNavigate();
    const { user, logOut } = useUserStore();

    const handleLogout = () => {
        logOut(); // Llamar a la función logOut del store
        navigate('/'); // Redirigir al inicio después de cerrar sesión
    };

    return (
        <AppBar
            position="relative"
            sx={{
                backgroundColor: colors.backgroundLight,
                color: colors.textDark,
                boxShadow: 3,
                zIndex: 1300,
            }}
        >
            <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                <Stack direction="row" alignItems="center">
                    {/* Logo */}
                    <img
                        src="/logoConTexto.png"
                        alt="Logo"
                        style={{ margin: "10px", height: '50px', transition: 'transform 0.3s ease, opacity 0.3s ease', cursor: 'pointer' }}
                        className="logo"
                        onClick={() => navigate('/')} // Redirigir al inicio al hacer clic en el logo
                    />
                </Stack>

                <Stack direction="row" gap={3} alignItems="center">
                    {/* Botón de Iniciar sesión solo si no está logueado */}
                    {!user ? (
                        <Button
                            variant="contained"
                            sx={{
                                backgroundColor: colors.textDark,
                                '&:hover': {
                                    backgroundColor: colors.secondary,
                                    color: colors.textDark,
                                },
                                display: 'flex',
                                alignItems: 'center',
                            }}
                            onClick={() => navigate('/signInLogIn')}
                        >
                            <Person sx={{ marginRight: 1 }} />
                            Iniciar sesión
                        </Button>
                    ) : (
                        <>
                            <ProfileCircle imageUrl={user.imageUrl} altText={user.name} />
                            {/* Mostrar solo el icono en pantallas pequeñas */}
                            <Button
                                sx={{
                                    display: { xs: 'none', sm: 'flex' }, // Ocultar en pantallas pequeñas
                                    color: colors.textDark,
                                    backgroundColor: colors.secondary,
                                    '&:hover': {
                                        backgroundColor: colors.primary,
                                        color: colors.secondary,
                                    },
                                    alignItems: 'center',
                                    whiteSpace: 'nowrap',
                                }}
                                onClick={handleLogout}
                            >
                                <Logout sx={{ marginRight: 1 }} />
                                Cerrar sesión
                            </Button>
                            <IconButton
                                sx={{
                                    display: { xs: 'flex', sm: 'none' },
                                    color: colors.textDark,
                                    backgroundColor: colors.secondary,
                                    '&:hover': {
                                        backgroundColor: colors.primary,
                                        color: colors.secondary,
                                    },
                                }}
                                onClick={handleLogout}
                            >
                                <Logout />
                            </IconButton>
                        </>
                    )}
                </Stack>
            </Toolbar>
        </AppBar>
    );
};

const ProfileCircle = ({ imageUrl, altText }) => {
    return (
        <Avatar
            src={imageUrl}
            alt={altText}
            sx={{
                width: 40, // Tamaño del círculo
                height: 40, // Tamaño del círculo
                border: '2px solid white', // Borde blanco
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)', // Sombra
            }}
        />
    );
};

export default Header;
