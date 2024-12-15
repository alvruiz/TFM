import styled from "styled-components";
import colors from "../../utils/colors";
import Typography from "@mui/material/Typography";
export const StyledTypographyTitle = styled(Typography)(({ theme }) => ({
    fontSize: '1.5rem',
    width: '100%',
    display: 'flex',
    fontWeight: 'bold',
    color: colors.primary,
}));

export const StyledTypographySubtitle = styled(Typography)(({ theme }) => ({
    fontSize: '1.1rem',
    width: '100%',
    display: 'flex',
    color: colors.primary,
}));
