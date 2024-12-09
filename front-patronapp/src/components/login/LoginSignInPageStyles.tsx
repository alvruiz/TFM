// LoginSignInPageStyles.ts
import styled from 'styled-components';
import { Box, Button, TextField, Typography, FormControl, Select, InputLabel, MenuItem, FormHelperText } from '@mui/material';
import colors from '../../utils/colors';

export const Root = styled(Box)`
  display: flex;
  flex-direction: row;
`;

export const ImageContainer = styled(Box)`
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  color: white;
  opacity: 0.9;
  z-index: 1;
`;

export const VideoBackground = styled.video`
  width: 100%;
  object-fit: cover;
  height: 100vh;
`;

export const LoginContainer = styled(Box)`
  padding-left: 10%;
  padding-right: 10%;
  background-color: rgba(246, 232, 214, 0.9);
  display: flex;
  flex-direction: column;
  justify-content: center;
  z-index: 1;
  height: 100vh;
`;

export const Title = styled(Typography)`
  color: ${colors.textDark};
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 10px;
`;

export const Subtitle = styled(Typography)`
  font-size: 1.4rem;
`;

export const StyledButton = styled(Button)`
  background-color: ${colors.textDark};
  &:hover {
    background-color: ${colors.secondary};
    color: ${colors.textDark};
  }
  display: flex;
  align-items: center;
`;

export const StyledTextField = styled(TextField)`
  margin-bottom: 16px;
  & .MuiOutlinedInput-root.Mui-focused fieldset {
    border-color: ${colors.textDark};
  }
  & .MuiInputLabel-root.Mui-focused {
    color: ${colors.textDark};
  }
`;

export const FormControlStyled = styled(FormControl)`
  margin-bottom: 16px;
`;

export const ToggleButton = styled(Button)`
  color: ${colors.textDark};
  &:hover {
    color: ${colors.secondary};
    background-color: ${colors.backgroundLight};
  }
`;
