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


export const StyledJoinButton = styled(Button)`
  color: ${colors.textDark};
  background-color: ${colors.secondary};
  &:hover {
    background-color: ${colors.textDark};
    color: ${colors.secondary};
  }
  width: 100%;
`;

export const StyledUnsuscribeButton = styled(Button)`
  color: ${colors.secondary};
  background-color: ${colors.textDark};
  &:hover {
    background-color: ${colors.secondary};
    color: ${colors.textDark};
  }
  display: flex;
  align-items: center;
`;

export const StyledDeleteButton = styled(Button)`
  color: white;
 background-color:rgb(201, 79, 79);
  &:hover {
    background-color: white;
    color: red;
  }
  display: flex;
  align-items: center;
`;

