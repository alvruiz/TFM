import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import useProvinceStore from '../../stores/province-store';
import { getVillages } from '../../services/get-villages.service';
import Header from '../header/Header';
import { StyledEngineProvider } from '@mui/material';
const VillagePage = () => {
    const { id } = useParams();
    const { getVillages, villages } = useProvinceStore();
    useEffect(() => {
        if (villages.length === 0) {
            console.log(id)

            getVillages(id);
        }
    }, [])
    return (
        <StyledEngineProvider injectFirst>
            <Header></Header>
            <div>
                <h1>Village Page</h1>
            </div>
        </StyledEngineProvider>
    );
};

export default VillagePage;