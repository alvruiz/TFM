import { Button } from '@mui/material';
import styled from 'styled-components';
import colors from '../../../utils/colors';

export const StyledCloseButton = styled(Button)`
  color: ${colors.textDark};
  background-color: ${colors.secondary};
  &:hover {
    background-color: ${colors.textDark};
    color: ${colors.secondary};
  }
  display: flex;
  align-items: center;
`;
