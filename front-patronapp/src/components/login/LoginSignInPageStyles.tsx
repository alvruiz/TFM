import { Box, Button, TextField, Typography, FormControl, Select } from '@mui/material';
import { styled } from '@mui/system';
import colors from '../../utils/colors';

export const Root = styled(Box)({
  display: 'flex',
  flexDirection: 'row',
  boxSizing: 'border-box',
});

export const ImageContainer = styled(Box)({
  position: 'absolute',
  top: '50%',
  transform: 'translateY(-50%)',
  width: '100%',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  textAlign: 'center',
  color: 'white',
  opacity: 0.9,
  zIndex: 1,
  overflow: 'hidden',
});

export const VideoBackground = styled('video')({
  height: '100vh',
  width: '100%',
  objectFit: 'fill',
  overflow: 'hidden',
});

export const LoginContainer = styled(Box)({
  paddingLeft: '8%',
  paddingRight: '8%',
  boxSizing: 'content-box',
  overflowY: 'auto',
  minHeight: '100%',
  backgroundColor: 'rgba(246, 232, 214, 0.9)',
  display: 'flex',
  flexDirection: 'column',
  gap: '12px',
  justifyContent: 'center',
  zIndex: 1,
});

export const Title = styled(Typography)({
  color: colors.textDark,
  fontSize: '2.5rem',
  fontWeight: 'bold',
  marginBottom: '10px',
});

export const Subtitle = styled(Typography)({
  fontSize: '1.4rem',
});

export const StyledButton = styled(Button)({
  backgroundColor: colors.textDark,
  '&:hover': {
    backgroundColor: colors.secondary,
    color: colors.textDark,
  },
  display: 'flex',
  alignItems: 'center',
});

export const StyledTextField = styled(TextField)({
  marginBottom: '8px',
  '& .MuiOutlinedInput-root.Mui-focused fieldset': {
    borderColor: colors.textDark,
  },
  '& .MuiInputLabel-root.Mui-focused': {
    color: colors.textDark,
  },
});

export const FormControlStyled = styled(FormControl)({});

export const ToggleButton = styled(Button)({
  color: colors.textDark,
  '&:hover': {
    color: colors.secondary,
    backgroundColor: colors.backgroundLight,
  },
});

export const StyledSelect = styled(Select)({
  marginBottom: '8px',
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
