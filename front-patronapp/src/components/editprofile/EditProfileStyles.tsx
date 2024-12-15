import { Box, Button, TextField, Typography, FormControl, Select } from '@mui/material';
import { styled } from '@mui/system';
import colors from '../../utils/colors'; // Asumo que tienes este archivo de colores



// Contenedor del formulario de edición de perfil
export const EditProfileContainer = styled(Box)({
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    width: '100%',
    padding: '50px',
    height: '100%',
    borderRadius: '8px',
    backgroundColor: 'white',
    boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
});

// Título de la página
export const Title = styled(Typography)({
    fontSize: '2rem',
    fontWeight: 'bold',
    color: colors.textDark,
    marginBottom: '20px',
});



// Estilo para los campos de texto
export const StyledTextField = styled(TextField)({
    marginBottom: '12px',
    width: '100%',
    '& .MuiOutlinedInput-root.Mui-focused fieldset': {
        borderColor: colors.textDark,
    },
    '& .MuiInputLabel-root.Mui-focused': {
        color: colors.textDark,
    },
});

// Botón de guardado
export const StyledButton = styled(Button)({
    backgroundColor: colors.primary,
    '&:hover': {
        backgroundColor: colors.secondary,
        color: colors.textDark,
    },
    width: '100%',
    padding: '12px',
    color: 'white',
    borderRadius: '5px',
    fontWeight: 'bold',
    marginTop: '20px',
});

// Estilo para el contenedor de imagen de perfil
export const ProfileImageContainer = styled(Box)({
    display: 'flex',
    justifyContent: 'center',
    marginBottom: '20px',
});


// Estilo para la selección de género
export const StyledSelect = styled(Select)({
    '& .MuiOutlinedInput-root': {
        '& .MuiOutlinedInput-notchedOutline': {
            borderColor: colors.textDark,
        },
        '&:hover .MuiOutlinedInput-notchedOutline': {
            borderColor: 'blue',
        },
        '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
            borderColor: 'green',
        },
    },
    '& .MuiInputLabel-root.Mui-focused': {
        color: colors.textDark,
    },
});
