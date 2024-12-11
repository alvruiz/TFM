import styled from 'styled-components';
import { Box, Button, TextField, Typography, FormControl } from '@mui/material';
import colors from '../../utils/colors';


export const Root = styled('div')({
     height: '100vh',
     display: 'flex',
     flexDirection: 'column',
});

export const Content = styled('div')({
     flex: 1, // El mapa ocupa el espacio restante
     overflow: 'hidden', // Evita scroll en el mapa
});