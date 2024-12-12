import { Button, Card, CardActionArea, TextField, Typography } from '@mui/material';
import styled from 'styled-components';
import colors from '../../utils/colors';

export const StyledCard = styled(Card)(() => ({
    transition: 'transform 0.3s, box-shadow 0.3s',
    '&:hover': {
        transform: 'scale(1.05)',
        boxShadow: "2"
    },
}));

export const StyledCardActionArea = styled(CardActionArea)(() => ({
    '&:hover .zoom-image': {
        transform: 'scale(1.1)',
    },
}));



export const SearchButtonStyled = styled(Button)(() => ({
    width: '100%',
    backgroundColor: colors.primary,
    color: 'white',
    '&:hover': {
        backgroundColor: colors.secondary,
        color: colors.primary
    },
}));


export const StyledTextField = styled(TextField)(({ theme }) => ({
    width: '100%',
    marginBottom: '8px',
    '& .MuiOutlinedInput-root.Mui-focused fieldset': {
        borderColor: colors.textDark,
    },
    '& .MuiInputLabel-root.Mui-focused': {
        color: colors.textDark,
    },
}));


export const StyledTypography = styled(Typography)(({ theme }) => ({
    fontSize: '1.5rem',
    width: '100%',
    display: 'flex',
    fontWeight: 'bold',
    alignItems: 'center',
    alignContent: 'center',
    justifyContent: 'center',
    textAlign: 'center',
    color: colors.primary,
}));
