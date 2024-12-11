import React from "react";
import { Stack, Button, Toolbar, Container, AppBar } from "@mui/material";
import { Logout, Person } from '@mui/icons-material';
import colors from "../../utils/colors";
import { useNavigate } from "react-router-dom";
import useUserStore from "../../stores/user-store";

const Header = () => {
    const navigate = useNavigate();
    const { user } = useUserStore();
    return (
        <AppBar
            position="sticky"
            sx={{
                backgroundColor: colors.backgroundLight,
                color: colors.textDark,
                boxShadow: 3,
                zIndex: 1300,
            }}
        >
            <Container sx={{ margin: 0, width: '100%' }} maxWidth={false}>
                <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                    <Stack direction="row" alignItems="center">
                        {/* Logo */}
                        <img
                            src="/logoConTexto.png"
                            alt="Logo"
                            style={{ height: '50px', transition: 'transform 0.3s ease, opacity 0.3s ease' }}
                            className="logo"
                        />
                    </Stack>

                    <Stack direction="row" gap={3} alignItems="center">
                        {/* Botón de Iniciar sesión solo si no está logueado */}
                        {user ? (
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
                                {/* Ícono de persona */}
                                <Person sx={{ marginRight: 1 }} />
                                Iniciar sesión
                            </Button>) :
                            <Button sx={{
                                color: colors.textDark, backgroundColor: colors.secondary, '&:hover': {
                                    backgroundColor: colors.primary,
                                    color: colors.secondary,
                                },
                                display: 'flex',
                                alignItems: 'center',
                            }} onClick={() => navigate('/signInLogIn')}>
                                <Logout sx={{ marginRight: 1 }}></Logout>
                                Cerrar sesión

                            </Button>}

                    </Stack>
                </Toolbar>
            </Container>
        </AppBar>
    );
};

export default Header;
