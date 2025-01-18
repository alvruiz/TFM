import React, { useEffect, useState } from 'react';
import { Modal, Box, Typography, IconButton, Button } from '@mui/material';
import ModalMap from '../../map/events/ModalMap';
import colors from '../../../utils/colors';
import { CalendarMonth, Description, Person, Close, Delete } from '@mui/icons-material';
import useUserStore from '../../../stores/user-store';
import { StyledJoinButton, StyledUnsuscribeButton, StyledDeleteButton } from './EventDetailsModalStyles';
import useEventStore from '../../../stores/event-store';
import useVillageStore from '../../../stores/village-store';
import { Role } from '../../../model/Role';

const EventDetailsModal = ({ village, open, selectedEvent, onClose }) => {
    const { getPersistedUser, user, setUser, getPersistedJwt } = useUserStore();
    const { joinEvent, deleteEvent } = useEventStore();
    const { getEvents } = useVillageStore();

    const [openConfirmModal, setOpenConfirmModal] = useState(false);

    useEffect(() => {
        if (!user) {
            getPersistedUser();
        }
    }, []);

    if (!selectedEvent || !selectedEvent.eventName) return null;

    const handleDeleteEvent = async () => {
        const jwt = getPersistedJwt();
        await deleteEvent(selectedEvent.id, jwt);
        await getEvents(village.festivity.id);
        onClose();
    };

    return (
        <>
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
                        position: 'relative',
                    }}
                >
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

                    <Typography variant="h5" sx={{ marginBottom: 2, fontSize: '24px', color: colors.textDark, fontWeight: 'bold' }}>
                        {selectedEvent.eventName}
                    </Typography>

                    <Typography variant="body1" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                        <Description sx={{ marginRight: 1, fontSize: '20px' }} />
                        <strong>Descripción: </strong> {selectedEvent.eventDescription}
                    </Typography>

                    <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                        <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                        <strong>Fecha de inicio: </strong> {new Date(selectedEvent.eventStartDate).toLocaleString()}
                    </Typography>

                    <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                        <CalendarMonth sx={{ marginRight: 1, fontSize: '20px' }} />
                        <strong>Fecha de fin: </strong> {new Date(selectedEvent.eventEndDate).toLocaleString()}
                    </Typography>

                    <Typography variant="body2" sx={{ marginBottom: 1, display: 'flex', alignItems: 'center', fontSize: '16px', color: colors.textDark }}>
                        <Person sx={{ marginRight: 1, fontSize: '20px' }} />
                        <strong>Capacidad: </strong> {selectedEvent.attendees.length}/{selectedEvent.eventMaxCapacity}
                    </Typography>

                    <ModalMap event={selectedEvent} />
                    <p>{user.eventsParticipating}</p>
                    {user && user.eventsParticipating && !user.eventsParticipating.includes(selectedEvent.id) && selectedEvent.attendees.length < selectedEvent.eventMaxCapacity &&
                        <StyledJoinButton
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

                    {user && (user.rol === Role.ADMIN || ((user.rol === Role.CM && user.villageId === village.id))) && (
                        <StyledDeleteButton
                            onClick={() => setOpenConfirmModal(true)}
                            sx={{
                                width: '100%',
                                display: 'block',
                                marginLeft: 'auto',
                                marginRight: 'auto',
                                marginTop: 2,
                            }}
                        >
                            <Delete sx={{ marginRight: 1 }} />
                            Eliminar evento
                        </StyledDeleteButton>
                    )}
                </Box>

            </Modal>

            <Modal
                open={openConfirmModal}
                onClose={() => setOpenConfirmModal(false)}
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
                        maxWidth: '500px',
                        width: '80%',
                    }}
                >
                    <Typography variant="h6" sx={{ marginBottom: 2, fontSize: '18px', color: colors.textDark, fontWeight: 'bold' }}>
                        ¿Estás seguro de que deseas eliminar este evento?
                    </Typography>

                    <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                        <Button
                            onClick={() => setOpenConfirmModal(false)}
                            sx={{
                                backgroundColor: colors.primary,
                                color: 'white',
                                ':hover': {
                                    backgroundColor: colors.secondary,
                                },
                            }}
                        >
                            Cancelar
                        </Button>
                        <Button
                            onClick={handleDeleteEvent}
                            sx={{
                                backgroundColor: colors.secondary,
                                color: colors.textDark,
                                ':hover': {
                                    backgroundColor: colors.textDark,
                                    color: colors.secondary,
                                },
                            }}
                        >
                            Eliminar
                        </Button>
                    </Box>
                </Box>
            </Modal>
        </>
    );
};

export default EventDetailsModal;
