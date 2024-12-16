import React from 'react';
import { Modal, Box, Typography, Button } from '@mui/material';
import ModalMap from '../../map/events/ModalMap';
import { StyledTypographySubtitle } from '../IndividualVillageStyles';
import { StyledButton } from '../../map/SpainMapStyle';
import colors from '../../../utils/colors';
import { StyledCloseButton } from './EventDetailsModalStyles';
import { CalendarMonth, Description, LocationOn, Person } from '@mui/icons-material';

const EventDetailsModal = ({ village, open, event, onClose }) => {
    if (!event) return null;

    return (
        <Modal
            open={open}
            onClose={onClose}
            sx={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                backdropFilter: 'blur(5px)',
            }}
        >
            <Box
                sx={{
                    backgroundColor: 'white',
                    padding: 4,
                    borderRadius: 2,
                    boxShadow: 24,
                    maxWidth: '600px',
                    width: '90%',
                }}
            >
                <Typography variant="h5" sx={{ marginBottom: 2, fontSize: '24px', color: colors.textDark, fontWeight: 'bold' }}>
                    {event.eventName}
                </Typography>

                {/* Descripción con icono */}
                <Typography variant="body1" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <Description sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Descripción: </strong> {event.eventDescription}
                </Typography>

                {/* Fecha de inicio con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Fecha de inicio: </strong> {new Date(event.eventStartDate).toLocaleString()}
                </Typography>

                {/* Fecha de fin con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Fecha de fin: </strong> {new Date(event.eventEndDate).toLocaleString()}
                </Typography>

                {/* Capacidad con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <Person sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Capacidad: </strong> {event.attendees.length}/{event.eventMaxCapacity}
                </Typography>

                {/* Coordenadas con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <LocationOn sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Coordenadas: </strong> Latitud {event.coords.latitude}, Longitud {event.coords.longitude}
                </Typography>

                {/* Mapa del evento */}
                <ModalMap event={event} />

                {/* Botón de cierre con estilo personalizado */}
                <StyledCloseButton
                    onClick={onClose}
                    sx={{
                        marginTop: 2,
                        display: 'block',
                        marginLeft: 'auto',
                        marginRight: 'auto',
                        color: colors.primary,
                    }}
                >
                    Cerrar
                </StyledCloseButton>
            </Box>
        </Modal>
    );
};

export default EventDetailsModal;
