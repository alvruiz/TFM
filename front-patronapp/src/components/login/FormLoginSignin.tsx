import React, { useEffect, useState } from 'react';
import { Box, FormHelperText, InputLabel, MenuItem, Select, StyledEngineProvider, Typography } from '@mui/material';
import Grid from "@mui/material/Grid2";
import {
    Root,
    ImageContainer,
    VideoBackground,
    LoginContainer,
    Title,
    Subtitle,
    StyledButton,
    StyledTextField,
    FormControlStyled,
    ToggleButton
} from './LoginSignInPageStyles';

const LoginSignInPage = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        name: '',
        surname: '',
        age: '',
        gender: '',
    });

    const [errors, setErrors] = useState({
        email: '',
        password: '',
        name: '',
        surname: '',
        age: '',
        gender: '',
    });

    useEffect(() => {
        setFormData({
            email: '',
            password: '',
            name: '',
            surname: '',
            age: '',
            gender: '',
        });
        setErrors({
            email: '',
            password: '',
            name: '',
            surname: '',
            age: '',
            gender: '',
        });
    }, [isLogin]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleFormSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const newErrors = { ...errors };

        // Validación del email
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailRegex.test(formData.email)) {
            newErrors.email = 'Por favor ingresa un correo electrónico válido.';
        } else {
            newErrors.email = '';
        }

        // Validación de la contraseña
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (formData.password.length < 8 || !passwordRegex.test(formData.password)) {
            newErrors.password = 'La contraseña debe tener al menos 8 caracteres, incluyendo letras y números.';
        } else {
            newErrors.password = '';
        }

        // Si hay errores, no enviamos el formulario
        if (Object.values(newErrors).some(error => error !== '')) {
            setErrors(newErrors);
            return;
        }

        // Si no hay errores, procedemos con el envío del formulario
        if (isLogin) {
            console.log('Iniciar sesión con:', formData.email, formData.password);
        } else {
            console.log('Registro con:', formData);
        }
    };

    return (
        <StyledEngineProvider injectFirst>
            <Root>
                <Grid container sx={{ maxHeight: '100vh', minWidth: '100vw' }}>
                    {/* Columna del Video */}
                    <Grid size={{ xs: 12, md: 7 }} sx={{ maxHeight: '100vh', height: '100vh', position: 'relative', backgroundColor: 'rgba(246, 232, 214, 0.9)' }}>
                        <ImageContainer>
                            <VideoBackground autoPlay loop muted>
                                <source src="/videos/portada.mp4" type="video/mp4" />
                                Tu navegador no soporta el elemento de video.
                            </VideoBackground>
                        </ImageContainer>
                        {/* Texto encima de la imagen */}
                        <ImageContainer>
                            <Box sx={{ display: 'flex', flexDirection: 'row' }}>
                                <Typography sx={{ fontSize: '2.5rem', fontWeight: 'bold', marginRight: '8px' }}>Hola!</Typography>
                                <Typography className="typewriter" sx={{ fontSize: '2.5rem', fontWeight: 'bold', marginRight: '8px' }}></Typography>

                            </Box>
                            <Subtitle>¿Nos vamos de fiesta a algún pueblo de España?</Subtitle>
                        </ImageContainer>
                    </Grid>

                    {/* Columna del Formulario */}
                    <Grid size={{ xs: 12, md: 5 }} sx={{ boxSizing: 'content-box', overflowY: 'auto', maxHeight: '100vh', height: '100%' }}>
                        <LoginContainer>
                            <Title align="center">
                                {isLogin ? 'Iniciar sesión' : 'Registrarse'}
                            </Title>
                            <form onSubmit={handleFormSubmit}>
                                {!isLogin && (
                                    <>
                                        <StyledTextField
                                            label="Nombre"
                                            variant="outlined"
                                            fullWidth
                                            name="name"
                                            value={formData.name}
                                            onChange={handleInputChange}
                                            required
                                            error={!!errors.name}
                                            helperText={errors.name}
                                        />
                                        <StyledTextField
                                            label="Apellidos"
                                            variant="outlined"
                                            fullWidth
                                            name="surname"
                                            value={formData.surname}
                                            onChange={handleInputChange}
                                            required
                                            error={!!errors.surname}
                                            helperText={errors.surname}
                                        />
                                    </>
                                )}
                                <StyledTextField
                                    label="Correo electrónico"
                                    variant="outlined"
                                    fullWidth
                                    name="email"
                                    value={formData.email}
                                    onChange={handleInputChange}
                                    required
                                    error={!!errors.email}
                                    helperText={errors.email}
                                />
                                <StyledTextField
                                    label="Contraseña"
                                    variant="outlined"
                                    type="password"
                                    fullWidth
                                    name="password"
                                    value={formData.password}
                                    onChange={handleInputChange}
                                    required
                                    error={!!errors.password}
                                    helperText={errors.password}
                                />
                                {!isLogin && (
                                    <>
                                        <StyledTextField
                                            label="Edad"
                                            variant="outlined"
                                            type="number"
                                            fullWidth
                                            name="age"
                                            value={formData.age}
                                            onChange={handleInputChange}
                                            required
                                            error={!!errors.age}
                                            helperText={errors.age}
                                        />
                                        <FormControlStyled fullWidth>
                                            <InputLabel>Sexo</InputLabel>
                                            <Select
                                                name="gender"
                                                value={formData.gender}
                                                onChange={handleInputChange}
                                                label="Sexo"
                                                required
                                                error={!!errors.gender}
                                            >
                                                <MenuItem value="male">Hombre</MenuItem>
                                                <MenuItem value="female">Mujer</MenuItem>
                                                <MenuItem value="other">Otro</MenuItem>
                                            </Select>
                                            <FormHelperText>{errors.gender}</FormHelperText>
                                        </FormControlStyled>
                                    </>
                                )}
                                <StyledButton variant="contained" fullWidth type="submit">
                                    {isLogin ? 'Iniciar sesión' : 'Registrarse'}
                                </StyledButton>
                            </form>
                            <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: 2 }}>
                                {isLogin && (
                                    <ToggleButton fullWidth variant="text" color="primary">
                                        ¿Olvidaste la contraseña?
                                    </ToggleButton>
                                )}
                                {!isLogin && (
                                    <ToggleButton fullWidth variant="text" color="primary" onClick={() => setIsLogin(!isLogin)}>
                                        ¿Ya tienes cuenta? Inicia sesión
                                    </ToggleButton>
                                )}
                                {isLogin && (
                                    <ToggleButton fullWidth variant="text" color="primary" onClick={() => setIsLogin(!isLogin)}>
                                        ¿No tienes cuenta? Regístrate
                                    </ToggleButton>
                                )}
                            </Box>
                        </LoginContainer>
                    </Grid>
                </Grid>
            </Root>
        </StyledEngineProvider >

    );
};

export default LoginSignInPage;
