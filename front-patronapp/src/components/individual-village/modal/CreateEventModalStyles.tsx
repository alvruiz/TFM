import { TextField } from "@mui/material";
import styled from "styled-components";
import colors from "../../../utils/colors";

export const StyledTextField = styled(TextField)({
    marginBottom: '12px',
    '& .MuiOutlinedInput-root.Mui-focused fieldset': {
        borderColor: colors.textDark,
    },
    '& .MuiInputLabel-root.Mui-focused': {
        color: colors.textDark,
    },
});