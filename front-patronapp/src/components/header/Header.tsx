import React, { useEffect, useState } from "react";
import { Stack, Button, Toolbar, AppBar, IconButton, Typography, useMediaQuery, Menu, MenuItem } from "@mui/material";
import { Add, Home, Logout, Person, Menu as MenuIcon, EventNote } from '@mui/icons-material';
import colors from "../../utils/colors";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import useUserStore from "../../stores/user-store";
import ProfileCircle from "./ProfileCircleHeader";
import { Content } from "../list/MainPageStyles";
import { Role } from "../../model/Role";
import CreateEventModal from "../individual-village/modal/CreateEventModal";
import { LogoutButton, StyledButton, StyledIconButton } from "./HeaderStyles";
import { toast } from "react-toastify";
import useEventStore from "../../stores/event-store";
import useVillageStore from "../../stores/village-store";

const Header = () => {
    const navigate = useNavigate();
    const { user, logOut } = useUserStore();
    const { id } = useParams();
    const location = useLocation();
    const currentRoute = location.pathname;

    // Estado para abrir/cerrar el modal
    const [modalOpen, setModalOpen] = useState(false);
    const { createEvent } = useEventStore();
    const { getEvents, events, village } = useVillageStore();
    const { authenticate, getPersistedUser } = useUserStore();
    // Detectar si estamos en una pantalla pequeña
    const isMobile = useMediaQuery('(max-width:600px)');

    // Estado para el menú desplegable
    const [anchorEl, setAnchorEl] = useState(null);
    const isMenuOpen = Boolean(anchorEl);
    console.log(user.rol === Role.ADMIN)
    // Función para abrir el menú
    const handleMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    // Función para cerrar el menú
    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    // Función para manejar el cierre del modal
    const handleCloseModal = () => {
        setModalOpen(false);
    };

    // Función para manejar la creación del evento
    const handleCreateEvent = async (eventData) => {
        console.log("Evento creado:", eventData);
        try {
            const jwt = await authenticate(getPersistedUser().email, getPersistedUser().password);
            await createEvent(eventData, village.festivity.id, jwt);
            await getEvents(village.festivity.id);


        } catch (error) {
            toast.error("Error al crear evento")
            console.error("Error al crear evento:", error);
        }
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
                                {!isMobile && currentRoute !== '/editProfile' && (
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
                                    </Stack>
                                )}
                                {isMobile ? (
                                    <>
                                        <IconButton onClick={handleMenuOpen}>
                                            <MenuIcon sx={{ color: colors.textDark }} />
                                        </IconButton>
                                        <Menu
                                            anchorEl={anchorEl}
                                            open={isMenuOpen}
                                            onClose={handleMenuClose}
                                            PaperProps={{
                                                style: { width: 200 },
                                            }}
                                        >
                                            <MenuItem onClick={() => navigate(`/village/${user.villageId}`)}>
                                                <Home sx={{ marginRight: 1 }} /> Ir a mi pueblo
                                            </MenuItem>
                                            {currentRoute === `/village/${id}` && (user.rol === Role.ADMIN || (user.rol === Role.CM && user.villageId === id)) && (
                                                <MenuItem onClick={() => setModalOpen(true)}>
                                                    <Add sx={{ marginRight: 1 }} /> Añadir evento
                                                </MenuItem>
                                            )}
                                            {user.rol === Role.ADMIN && (
                                                <MenuItem onClick={() => navigate('/agenda')}>
                                                    <EventNote sx={{ marginRight: 1 }} /> Agenda
                                                </MenuItem>
                                            )}
                                            <MenuItem onClick={handleLogout}>
                                                <Logout sx={{ marginRight: 1 }} /> Cerrar sesión
                                            </MenuItem>
                                        </Menu>
                                    </>
                                ) : (
                                    <>
                                        {user.rol === Role.CM && currentRoute !== `/village/${user.villageId}` && (
                                            <StyledButton
                                                onClick={() => navigate(`/village/${user.villageId}`)}
                                            >
                                                <Home />
                                            </StyledButton>
                                        )}
                                        {currentRoute === `/village/${id}` && (user.rol === Role.ADMIN || (user.rol === Role.CM && user.villageId === id)) && (
                                            <StyledButton onClick={() => setModalOpen(true)}>
                                                <Add />
                                                <Typography variant="body2">Añadir evento</Typography>
                                            </StyledButton>
                                        )}
                                        {(user.rol === Role.ADMIN || user.rol === Role.USER) && (
                                            <StyledButton onClick={() => navigate('/agenda')}>
                                                <EventNote />
                                                <Typography variant="body2">Agenda</Typography>
                                            </StyledButton>
                                        )}
                                        <LogoutButton onClick={handleLogout}>
                                            <Logout />
                                            <Typography variant="body2" sx={{ fontWeight: 'bold' }}>
                                                Cerrar sesión
                                            </Typography>
                                        </LogoutButton>
                                    </>
                                )}
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
