import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import useProvinceStore from '../../stores/province-store';
import Header from '../header/Header';
import { Button, CardActions, CardContent, CardMedia, StyledEngineProvider, Typography } from '@mui/material';
import Grid from "@mui/material/Grid2";
import { CalendarMonth } from '@mui/icons-material';
import colors from '../../utils/colors';
import { StyledCard, StyledCardActionArea } from './VillaePageStyle';
import AdvancedSearch from './AdvancedSearch';
import { Village } from '../../model/Village';

const VillagePage = () => {
    const { id } = useParams();
    const { getVillages, getFestivities, festivities, villages } = useProvinceStore();
    const [page, setPage] = useState(0);
    const [loadedVillages, setLoadedVillages] = useState([])
    const itemsPerPage = 9;
    // Cargar al inicio
    useEffect(() => {

        const getVillagesAsync = async () => {
            if (loadedVillages.length === 0) {

                setLoadedVillages(await getVillages(id, page, itemsPerPage))
            }
        }
        getVillagesAsync();

    }, []);

    useEffect(() => {
    }, [loadedVillages])
    // Al llegar al final haz scroll infinito
    const onScrollAction = async (event) => {
        const { scrollTop, scrollHeight, clientHeight } = event.target;
        if (scrollHeight - scrollTop === clientHeight) {
            setPage(prevPage => {
                const nextPage = prevPage + 1;
                console.log(nextPage);
                getVillages(id, nextPage, itemsPerPage).then(newVillages => {
                    setLoadedVillages(prevVillages => [...prevVillages, ...newVillages]);

                });
                return nextPage;
            });
        }
    }

    return (
        <StyledEngineProvider injectFirst>
            <Header />
            <div onScroll={onScrollAction} style={{ overflowY: 'auto', maxHeight: '80vh' }} // Asegura que el contenedor pueda hacer scroll
            >
                <Grid container spacing={4} sx={{ padding: 3 }}>
                    <Grid size={{ xs: 12 }}>
                        <AdvancedSearch />
                    </Grid>

                    {
                        loadedVillages.map((village, index) => (
                            <Grid size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={index}>
                                <StyledCard
                                    sx={{
                                        transition: 'transform 0.3s, box-shadow 0.3s',
                                        '&:hover': {
                                            transform: 'scale(1.05)',
                                            boxShadow: 6,
                                        },
                                    }}
                                >
                                    <StyledCardActionArea
                                        sx={{
                                            '&:hover .zoom-image': {
                                                transform: 'scale(1.1)',
                                            },
                                        }}
                                    >
                                        <CardMedia
                                            className="zoom-image"
                                            component="img"
                                            height="140"
                                            image={village.imageUrl}
                                            alt={village.name}
                                            sx={{
                                                transition: 'transform 0.3s',
                                            }}
                                        />
                                        <CardContent>
                                            <Typography gutterBottom variant="h5" component="div">
                                                {village.name}
                                            </Typography>
                                            <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                                                <CalendarMonth />
                                                {festivities.find(festivity => festivity.villageId === village.id)?.startDate} - {festivities.find(festivity => festivity.villageId === village.id)?.endDate}
                                            </Typography>
                                        </CardContent>
                                    </StyledCardActionArea>
                                    <CardActions>
                                        <Button size="small" sx={{ color: colors.primary }}>
                                            ¡Ver programa festivo!
                                        </Button>
                                    </CardActions>
                                </StyledCard>
                            </Grid>
                        ))
                    }
                </Grid>
            </div>
        </StyledEngineProvider>
    );
};

export default VillagePage;
