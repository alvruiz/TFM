import { Button, TextField, Typography } from '@mui/material';
import Grid from "@mui/material/Grid2";
import colors from '../../utils/colors';
import { SearchButtonStyled, StyledTextField, StyledTypography } from './VillagesPageStyle';
import useProvinceStore from '../../stores/province-store';
import ProvinceMap from '../map/village/ProvinceMap';
import { useState } from 'react';
const AdvancedSearch = () => {
    const { actualProvince, actualVillages, setActualVillages, villages, resetPageAndAmountItems } = useProvinceStore();

    const [searchTerm, setSearchTerm] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    const handleSearch = () => {
        resetPageAndAmountItems();
        const filteredVillages = villages.filter(village => {
            const nameMatches = village.name.toLowerCase().includes(searchTerm.toLowerCase());
            const startDateMatches = startDate ? new Date(village.festivity.startDate) >= new Date(startDate) : true;
            const endDateMatches = endDate ? new Date(village.festivity.endDate) <= new Date(endDate) : true;
            return nameMatches && startDateMatches && endDateMatches;
        });
        setActualVillages(filteredVillages);
    };

    const handleSubmit = (e) => {
        e.preventDefault(); // Evita que el formulario se envíe por defecto
        handleSearch(); // Llama a la lógica de búsqueda
    };

    return (
        <form onSubmit={handleSubmit}>
            <Grid container spacing={2} sx={{
                padding: 4,
                overflow: 'hidden',
                borderRadius: 10,
                backgroundColor: colors.backgroundLight,
                boxShadow: 3,
                '&:hover': {
                    boxShadow: 6,
                },
            }}>
                <StyledTypography>
                    ¿Te ayudo a buscar fiestas de algún pueblo de {actualProvince?.name ? actualProvince.name : 'España'}?
                </StyledTypography>

                <Grid size={{ xs: 12 }}>
                    <StyledTextField
                        sx={{ width: "100%" }}
                        id="search-bar"
                        label="Introduce el nombre de un pueblo"
                        variant="outlined"
                        placeholder="Buscar..."
                        size="small"
                        value={searchTerm}
                        onChange={e => setSearchTerm(e.target.value)}
                    />
                </Grid>

                <Grid size={{ xs: 12, sm: 6 }}>
                    <StyledTextField
                        id="date-filter"
                        label="Fecha de inicio de festividad"
                        type="date"
                        variant="outlined"
                        size="small"
                        InputLabelProps={{ shrink: true }}
                        value={startDate}
                        onChange={e => setStartDate(e.target.value)}
                    />
                </Grid>

                <Grid size={{ xs: 12, sm: 6 }}>
                    <StyledTextField
                        id="date-filter"
                        label="Fecha de fin de festividad"
                        type="date"
                        variant="outlined"
                        size="small"
                        InputLabelProps={{ shrink: true }}
                        value={endDate}
                        onChange={e => setEndDate(e.target.value)}
                    />
                </Grid>

                <Grid size={{ xs: 12, sm: 12 }}>
                    <SearchButtonStyled type="submit">
                        Buscar
                    </SearchButtonStyled>
                </Grid>
                <ProvinceMap
                    actualProvince={actualProvince}
                    actualVillages={actualVillages}
                />
            </Grid>
        </form>
    );
};

export default AdvancedSearch;