import React, { useEffect, useState } from "react";
import { Stack, Button, Toolbar, Container, AppBar, IconButton, Typography } from "@mui/material";
import { Add, Home, Logout, Person } from '@mui/icons-material';
import colors from "../../utils/colors";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import useUserStore from "../../stores/user-store";
import ProfileCircle from "./ProfileCircleHeader";
import { Content } from "../list/MainPageStyles";
import { Role } from "../../model/Role";
import CreateEventModal from "../individual-village/modal/CreteEventModal";
import useVillageStore from "../../stores/village-store";

const Header = () => {
    const navigate = useNavigate();
    const { user, logOut } = useUserStore();
    const { getVillage } = useVillageStore();
    const { id } = useParams();
    const location = useLocation();
    const currentRoute = location.pathname;

    // Estado para abrir/cerrar el modal
    const [modalOpen, setModalOpen] = useState(false);

    // Función para manejar el cierre del modal
    const handleCloseModal = () => {
        setModalOpen(false);
    };

    useEffect(() => {
        if (user && user.villageId) {
            getVillage(user.villageId);
        }
    }, [user]);

    // Función para manejar la creación del evento
    const handleCreateEvent = (eventData) => {
        console.log("Evento creado:", eventData);
        // Aquí puedes agregar la lógica para enviar los datos del evento al backend
        handleCloseModal();
    };

    // Función de logout
    const handleLogout = () => {
        logOut();
        navigate('/'); // Redirigir al inicio después de cerrar sesión
    };

    return (
        <Content>
            <AppBar
                position="relative"
                sx={{
                    backgroundColor: colors.backgroundLight,
                    overflow: 'hidden',
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
                                {currentRoute !== '/editProfile' && (
                                    <Stack direction="row" alignItems="center">
                                        {user.rol === Role.CM && (
                                            <Typography
                                                sx={{
                                                    marginRight: 2,
                                                    color: colors.textDark,
                                                    fontWeight: 'bold',
                                                    fontSize: '16px',
                                                }}
                                            >
                                                Community Manager
                                            </Typography>
                                        )}
                                        {user.rol === Role.ADMIN && (
                                            <Typography
                                                sx={{
                                                    marginRight: 2,
                                                    color: colors.textDark,
                                                    fontWeight: 'bold',
                                                    fontSize: '16px',
                                                }}
                                            >
                                                Administrador
                                            </Typography>
                                        )}
                                        <ProfileCircle imageUrl={user.imageUrl} altText={user.name} width={40} height={40} />
                                    </Stack>)}
                                {user.rol === Role.CM && currentRoute !== `/village/${user.villageId}` && (
                                    <Button
                                        sx={{
                                            display: { xs: 'none', sm: 'flex' }, // Ocultar en pantallas pequeñas
                                            color: colors.secondary,
                                            backgroundColor: colors.textDark,
                                            '&:hover': {
                                                backgroundColor: colors.secondary,
                                                color: colors.textDark,
                                            },
                                            alignItems: 'center',
                                            whiteSpace: 'nowrap',
                                        }}
                                        onClick={() => navigate(`/village/${user.villageId}`)} // Redirigir al pueblo del alcalde
                                    >
                                        <Home />
                                    </Button>
                                )
                                }
                                {currentRoute === `/village/${id}` && (user.rol === Role.ADMIN || (user.rol === Role.CM && user.villageId === id)) && (
                                    <Button
                                        sx={{
                                            display: { xs: 'none', sm: 'flex' }, // Ocultar en pantallas pequeñas
                                            color: colors.secondary,
                                            backgroundColor: colors.textDark,
                                            '&:hover': {
                                                backgroundColor: colors.secondary,
                                                color: colors.textDark,
                                            },
                                            alignItems: 'center',
                                            whiteSpace: 'nowrap',
                                        }}
                                        onClick={() => setModalOpen(true)} // Abrir el modal
                                    >
                                        <Add sx={{ marginRight: 1 }} />
                                        Añadir evento
                                    </Button>
                                )}

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

            {/* Modal de Crear Evento */}
            <CreateEventModal
                open={modalOpen}
                onClose={handleCloseModal}
                onCreate={handleCreateEvent}
            />
        </Content>
    );
};

export default Header;
