import React, { useEffect, useState } from 'react';
import { Root } from './MainListStyles';
import Grid from "@mui/material/Grid2";
import { StyledEngineProvider } from '@mui/material';
import SpainMap from '../map/SpainMap';
import Header from '../header/Header';
import useProvinceStore from '../../stores/province-store';


const MainList = () => {
    const { provinces, isLoading, error, getProvinces } = useProvinceStore();
    useEffect(() => {
        if (provinces.length === 0) {
            getProvinces();
        }
    }, []);
    return (
        <StyledEngineProvider injectFirst>

            <Root>
                <Header></Header>

                <Grid container>
                    <Grid size={{ xs: 12, md: 12 }} sx={{ position: 'relative', backgroundColor: 'rgba(246, 232, 214, 0.9)' }}>
                        <SpainMap></SpainMap>
                    </Grid>
                </Grid>
            </Root>
        </StyledEngineProvider>
    );
};

export default MainList;