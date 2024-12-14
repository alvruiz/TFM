import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import useProvinceStore from '../../stores/province-store';
import Header from '../header/Header';
import { CardContent, CardMedia, StyledEngineProvider, Typography, Button } from '@mui/material';
import Grid from "@mui/material/Grid2";
import { CalendarMonth, ArrowDownward } from '@mui/icons-material';
import colors from '../../utils/colors';
import { StyledCard, StyledCardActionArea } from './VillagesPageStyle';
import AdvancedSearch from './AdvancedSearch';

const VillagesPage = () => {
    const { id } = useParams();
    const { getVillages, actualVillages, setActualVillages, getProvinces, actualPage, setActualPage, amountItems, setAmountItems } = useProvinceStore();
    const [isLoading, setIsLoading] = useState(false);
    const [itemsPerPage, setItemsPerPage] = useState(9);

    const [hasMore, setHasMore] = useState(true);


    // Controlar la cantidad de elementos por página según el ancho de la ventana
    const calculateItemsPerPage = () => {
        const containerWidth = window.innerWidth;
        let columns;
        if (containerWidth >= 1200) columns = 4;
        else if (containerWidth >= 900) columns = 3;
        else if (containerWidth >= 600) columns = 2;
        else columns = 1;

        if (columns === 4) return 12;
        if (columns === 3) return 9;
        if (columns === 2) return 6;
        return 3;
    };

    useEffect(() => {
        const updateItemsPerPage = () => {
            setItemsPerPage(calculateItemsPerPage());
            setAmountItems(calculateItemsPerPage());
        };

        updateItemsPerPage();
        window.addEventListener('resize', updateItemsPerPage);

        return () => {
            window.removeEventListener('resize', updateItemsPerPage);
        };
    }, []);

    // Cargar los pueblos iniciales
    useEffect(() => {
        const fetchInitialVillages = async () => {
            setIsLoading(true);
            await getProvinces();

            const initialVillages = await getVillages(id);
            setActualVillages(initialVillages); // Guardar todos los pueblos en el store
            setIsLoading(false);
            if (initialVillages.length < itemsPerPage) {
                setHasMore(false); // No hay más pueblos
            }
        };

        fetchInitialVillages();
    }, [id]);

    // Cargar más pueblos cuando se cambia la página
    useEffect(() => {
        if (actualPage > 0 && hasMore && !isLoading) {
            const fetchMoreVillages = async () => {
                setIsLoading(true);
                const newVillages = await getVillages(id);

                if (amountItems + itemsPerPage > actualVillages.length) {
                    setAmountItems(actualVillages.length)
                } else {
                    setAmountItems(amountItems + itemsPerPage)
                }

                setIsLoading(false);
                if (newVillages.length < itemsPerPage) {
                    setHasMore(false); // No hay más pueblos
                }
            };

            fetchMoreVillages();
        }
    }, [actualPage]);

    // Control del botón "Cargar más"
    const handleLoadMore = () => {
        if (!isLoading && hasMore) {
            setActualPage(actualPage + 1);
        }
    };

    return (
        <StyledEngineProvider injectFirst>
            <div style={{ overflowY: 'auto', minHeight: '101vh' }}>
                <Header />
                <div style={{ margin: '0 36px' }}>
                    <Grid container spacing={4} sx={{ padding: 3 }}>
                        <Grid size={{ xs: 12 }}>
                            <AdvancedSearch />
                        </Grid>
                        {actualVillages.slice(0, amountItems).map((village, index) => (
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
                                            <Typography
                                                gutterBottom
                                                variant="h5"
                                                component="div"
                                                sx={{
                                                    whiteSpace: 'nowrap',
                                                    overflow: 'hidden',
                                                    textOverflow: 'ellipsis',
                                                }}
                                            >
                                                {village.name}
                                            </Typography>
                                            <Typography variant="body2" sx={{ color: colors.textDark, display: 'flex', alignItems: 'center' }}>
                                                <CalendarMonth sx={{ color: colors.textDark, marginRight: 0.5, verticalAlign: 'middle' }} />
                                                {village?.festivity?.startDate ?? ''} -{' '}
                                                {village?.festivity?.endDate ?? ''}
                                            </Typography>
                                        </CardContent>
                                    </StyledCardActionArea>
                                </StyledCard>
                            </Grid>
                        ))}
                    </Grid>

                    {hasMore && !isLoading && actualVillages.length !== amountItems && actualVillages.length > itemsPerPage && (
                        <div style={{ textAlign: 'center', marginTop: '20px' }}>
                            <Button
                                onClick={handleLoadMore}
                                variant="outlined"
                                endIcon={<ArrowDownward />}
                                sx={{ color: colors.primary, backgroundColor: colors.backgroundLight, border: 'none' }}
                            >
                                Cargar más elementos
                            </Button>
                        </div>
                    )}
                </div>
            </div>
        </StyledEngineProvider>
    );
};

export default VillagesPage;
