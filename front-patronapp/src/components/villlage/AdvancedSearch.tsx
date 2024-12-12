import { Button, TextField, Typography } from '@mui/material';
import Grid from "@mui/material/Grid2";
import colors from '../../utils/colors';
import { SearchButtonStyled, StyledTextField, StyledTypography } from './VillaePageStyle';
import useProvinceStore from '../../stores/province-store';
const AdvancedSearch = () => {
    const { actualProvince } = useProvinceStore();
    return (
        <form>
            <Grid container spacing={2} sx={{
                padding: 4,
                backgroundColor: colors.backgroundLight,
                boxShadow: 3,
                borderRadius: 2,
                '&:hover': {
                    boxShadow: 6,
                },


            }}>
                <StyledTypography>
                    ¿Te ayudo a buscar fiestas de algún pueblo de {actualProvince?.name ? actualProvince.name : 'España'}?
                </StyledTypography>

                {/* Filtro de Ciudad */}
                <Grid size={{ xs: 12 }}>
                    <StyledTextField
                        sx={{ width: "100%" }}
                        id="search-bar"
                        className="text"
                        label="Introduce el nombre de un pueblo"
                        variant="outlined"
                        placeholder="Buscar..."
                        size="small"
                    />
                </Grid>

                {/* Filtro de Fecha */}
                <Grid size={{ xs: 12, sm: 6 }}>
                    <StyledTextField
                        id="date-filter"
                        className="date-filter"
                        label="Fecha de inicio de festividad"
                        type="date"
                        variant="outlined"
                        size="small"
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>
                {/* Filtro de Fecha */}
                <Grid size={{ xs: 12, sm: 6 }}>
                    <StyledTextField
                        id="date-filter"
                        className="date-filter"
                        label="Fecha de fin de festividad"
                        type="date"
                        variant="outlined"
                        size="small"

                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>
                <Grid size={{ xs: 12, sm: 12 }}>
                    <SearchButtonStyled>
                        Search
                    </SearchButtonStyled>
                </Grid>
            </Grid>
        </form >
    );
};

export default AdvancedSearch;
