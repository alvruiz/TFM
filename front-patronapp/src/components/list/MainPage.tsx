import React, { useEffect, useState } from 'react';
import { Root } from './MainPageStyles';
import Grid from "@mui/material/Grid2";
import { StyledEngineProvider } from '@mui/material';
import SpainMap from '../map/SpainMap';
import Header from '../header/Header';
import useProvinceStore from '../../stores/province-store';


const MainPage = () => {
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

                <SpainMap></SpainMap>
            </Root>
        </StyledEngineProvider>
    );
};

export default MainPage;