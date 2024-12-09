import React from "react";
import {
    Stack,
    Button,
    Toolbar,
    Container,
    AppBar,
} from "@mui/material";
import { Person } from '@mui/icons-material';  // Importar el ícono
import colors from "../../utils/colors";
import { useNavigate } from "react-router-dom";

const Header = () => {
    const navigate = useNavigate(); // Crea la función de navegación

    return (
        <AppBar sx={{ backgroundColor: colors.backgroundLight, color: colors.textDark }}>
            <Container sx={{ margin: 0, width: '100%' }} maxWidth={false}>
                <Toolbar>
                    <Stack
                        direction="row"
                        justifyContent="space-between"
                        alignItems="center"
                        width="100%"
                    >
                        {/* Logo */}
                        <img
                            src="/logoConTexto.png"
                            alt="Logo"
                            style={{ height: '50px', transition: 'transform 0.3s ease, opacity 0.3s ease' }}
                            className="logo"
                        />
                        <Stack direction="row" gap={3} justifyContent="flex-end" width="auto">
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
                                <Person sx={{ marginRight: 1 }} /> {/* El margen derecho es opcional */}
                                Iniciar sesión
                            </Button>
                        </Stack>
                    </Stack>
                </Toolbar>
            </Container>
        </AppBar>
    );
};

export default Header;
