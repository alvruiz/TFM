import { Button, IconButton } from '@mui/material';
import { styled } from '@mui/system';
import colors from '../../utils/colors';

export const StyledButton = styled(Button)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
    whiteSpace: 'nowrap',
    color: colors.secondary,
    backgroundColor: colors.textDark,
    '&:hover': {
        backgroundColor: colors.secondary,
        color: colors.textDark,
    },
    gap: '8px',
    padding: '6px 12px',
}));

export const LogoutButton = styled(Button)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    textAlign: 'center',
    whiteSpace: 'nowrap',
    color: colors.textDark,
    backgroundColor: colors.secondary,
    '&:hover': {
        backgroundColor: colors.primary,
        color: colors.secondary,
    },
    gap: '8px',
    padding: '6px 12px',
}));

export const StyledIconButton = styled(IconButton)(({ theme }) => ({
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    color: colors.textDark,
    backgroundColor: colors.secondary,
    '&:hover': {
        backgroundColor: colors.primary,
        color: colors.secondary,
    },
}));
