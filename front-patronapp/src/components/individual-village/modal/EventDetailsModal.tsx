import React, { useEffect, useState } from 'react';
import { Modal, Box, Typography, IconButton } from '@mui/material';
import ModalMap from '../../map/events/ModalMap';
import { StyledTypographySubtitle } from '../IndividualVillageStyles';
import { StyledButton } from '../../map/SpainMapStyle';
import colors from '../../../utils/colors';
import { CalendarMonth, Description, LocationOn, Person, Close } from '@mui/icons-material';
import useUserStore from '../../../stores/user-store';
import { StyledJoinButton, StyledUnsuscribeButton } from './EventDetailsModalStyles';
import useEventStore from '../../../stores/event-store';
import useVillageStore from '../../../stores/village-store';
import useModalStore from '../../../stores/modal-store';
const EventDetailsModal = ({ village, open, selectedEvent, onClose }) => {
    const { getPersistedUser, user, setUser } = useUserStore();

    const { joinEvent } = useEventStore();

    useEffect(() => {
        if (!user) {
            getPersistedUser();
        }
    }, []);


    if (!selectedEvent || !selectedEvent.eventName) return null;

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
                    maxWidth: '900px',
                    width: '90%',
                    position: 'relative', // Necesario para posicionar la "X" correctamente
                }}
            >
                {/* Botón "X" para cerrar */}
                <IconButton
                    onClick={onClose}
                    sx={{
                        position: 'absolute',
                        top: 8,
                        right: 8,
                        color: colors.textDark,
                    }}
                >
                    <Close />
                </IconButton>

                {/* Título del evento */}
                <Typography variant="h5" sx={{ marginBottom: 2, fontSize: '24px', color: colors.textDark, fontWeight: 'bold' }}>
                    {selectedEvent.eventName}
                </Typography>

                {/* Descripción con icono */}
                <Typography variant="body1" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <Description sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Descripción: </strong> {selectedEvent.eventDescription}
                </Typography>

                {/* Fecha de inicio con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Fecha de inicio: </strong> {new Date(selectedEvent.eventStartDate).toLocaleString()}
                </Typography>

                {/* Fecha de fin con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Fecha de fin: </strong> {new Date(selectedEvent.eventEndDate).toLocaleString()}
                </Typography>

                {/* Capacidad con icono */}
                <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                    <Person sx={{ marginRight: 1, fontSize: '20px' }} />
                    <strong>Capacidad: </strong> {selectedEvent.attendees.length}/{selectedEvent.eventMaxCapacity}
                </Typography>


                {/* Mapa del evento */}
                <ModalMap event={selectedEvent} />

                {/* Opcion de apuntarse si el usuario no está apuntado al evento */}
                {user && user.eventsParticipating && !user.eventsParticipating.includes(selectedEvent.id) && selectedEvent.attendees.length < selectedEvent.eventMaxCapacity &&
                    < StyledJoinButton
                        onClick={async () => {
                            setUser(await joinEvent(user.email, selectedEvent.id));
                        }}
                        sx={{
                            marginTop: 5,
                            width: '100%',
                            display: 'block',
                            marginLeft: 'auto',
                            marginRight: 'auto',
                            backgroundColor: colors.backgroundLight,
                            color: 'white',
                            ':hover': {
                                backgroundColor: colors.textDark,
                            },
                        }}
                    >
                        Apuntarse
                    </StyledJoinButton>
                }
                {user && user.eventsParticipating && user.eventsParticipating.includes(selectedEvent.id) &&
                    <StyledUnsuscribeButton
                        onClick={async () => {
                            setUser(await joinEvent(user.email, selectedEvent.id));
                        }}
                        sx={{
                            marginTop: 5,
                            width: '100%',
                            display: 'block',
                            marginLeft: 'auto',
                            marginRight: 'auto',
                            backgroundColor: colors.secondary,
                            color: colors.textDark,
                            ':hover': {
                                backgroundColor: colors.textDark,
                                color: colors.secondary,
                            },
                        }}
                    >
                        Desapuntarse
                    </StyledUnsuscribeButton>
                }
            </Box>
        </Modal >
    );
};

export default EventDetailsModal;


