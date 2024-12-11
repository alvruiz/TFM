import { Button } from '@mui/material';
import styled from 'styled-components';
import colors from '../../utils/colors';

export const StyledButton = styled(Button)`
  background-color: ${colors.textDark};
  &:hover {
    background-color: ${colors.secondary};
    color: ${colors.textDark};
  }
  display: flex;
  align-items: center;
`;
