import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, IconButton, TextField, Button } from '@mui/material';
import { Close } from '@mui/icons-material';
import colors from '../../../utils/colors';
import { StyledTextField } from './CreateEventModalStyles';

const CreateEventModal = ({ open, onClose, onCreate }) => {
    const [eventData, setEventData] = useState({
        eventName: '',
        eventDescription: '',
        eventStartDate: '',
        eventEndDate: '',
        coords: {
            latitude: '',
            longitude: '',
        },
        eventMaxCapacity: '',
    });

    // Estados para los errores de validación
    const [capacityError, setCapacityError] = useState(false);
    const [startDateError, setStartDateError] = useState(false);
    const [endDateError, setEndDateError] = useState(false);

    // Limpiar los campos cuando el modal se cierra
    useEffect(() => {
        if (!open) {
            setEventData({
                eventName: '',
                eventDescription: '',
                eventStartDate: '',
                eventEndDate: '',
                coords: {
                    latitude: '',
                    longitude: '',
                },
                eventMaxCapacity: '',
            });
            setCapacityError(false);
            setStartDateError(false);
            setEndDateError(false);
        }
    }, [open]);

    const handleChange = (field, value) => {
        if (field.includes('coords.')) {
            const coordField = field.split('.')[1];
            setEventData(prev => ({
                ...prev,
                coords: {
                    ...prev.coords,
                    [coordField]: value,
                },
            }));
        } else {
            setEventData(prev => ({
                ...prev,
                [field]: value,
            }));
        }
    };

    const handleMaxCapacityChange = (value) => {
        if (value < 0) {
            setCapacityError(true);  // Mostrar error si el valor es negativo
        } else {
            setCapacityError(false); // Quitar error si el valor es válido
        }
        setEventData(prev => ({
            ...prev,
            eventMaxCapacity: value,
        }));
    };

    // Verificar si las fechas son válidas
    const handleDateValidation = () => {
        const today = new Date().toISOString().slice(0, 16); // Fecha actual en formato ISO (yyyy-mm-ddThh:mm)
        const startDateValid = eventData.eventStartDate >= today;
        const endDateValid = eventData.eventEndDate > eventData.eventStartDate;

        setStartDateError(eventData.eventStartDate && !startDateValid); // Solo mostrar error si el campo no está vacío
        setEndDateError(eventData.eventEndDate && !endDateValid); // Solo mostrar error si el campo no está vacío

        return startDateValid && endDateValid;
    };

    useEffect(() => {
        // Comprobar las fechas y capacidad siempre que haya un cambio
        handleDateValidation();
    }, [eventData]);

    const handleSubmit = () => {
        if (capacityError || !handleDateValidation()) {
            return; // No enviar si hay error en la capacidad o las fechas
        }
        onCreate(eventData);
        onClose();
    };

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
                    position: 'relative',
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

                {/* Título */}
                <Typography variant="h5" sx={{ marginBottom: 3, fontSize: '24px', color: colors.textDark, fontWeight: 'bold' }}>
                    Crear Nuevo Evento
                </Typography>

                {/* Campos del formulario */}
                <StyledTextField
                    label="Nombre del Evento"
                    variant="outlined"
                    fullWidth
                    value={eventData.eventName}
                    onChange={e => handleChange('eventName', e.target.value)}
                    sx={{ marginBottom: 2 }}
                />
                <StyledTextField
                    label="Descripción del Evento"
                    variant="outlined"
                    multiline
                    rows={3}
                    fullWidth
                    value={eventData.eventDescription}
                    onChange={e => handleChange('eventDescription', e.target.value)}
                    sx={{ marginBottom: 2 }}
                />
                <StyledTextField
                    label="Fecha de Inicio"
                    type="datetime-local"
                    variant="outlined"
                    fullWidth
                    value={eventData.eventStartDate}
                    onChange={e => handleChange('eventStartDate', e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    sx={{ marginBottom: 2 }}
                    error={startDateError}
                    helperText={startDateError ? 'La fecha de inicio debe ser posterior a hoy.' : ''}
                />
                <StyledTextField
                    label="Fecha de Fin"
                    type="datetime-local"
                    variant="outlined"
                    fullWidth
                    value={eventData.eventEndDate}
                    onChange={e => handleChange('eventEndDate', e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    sx={{ marginBottom: 2 }}
                    error={endDateError}
                    helperText={endDateError ? 'La fecha de fin debe ser posterior a la fecha de inicio.' : ''}
                />
                <StyledTextField
                    label="Latitud"
                    variant="outlined"
                    fullWidth
                    value={eventData.coords.latitude}
                    onChange={e => handleChange('coords.latitude', e.target.value)}
                    sx={{ marginBottom: 2 }}
                />
                <StyledTextField
                    label="Longitud"
                    variant="outlined"
                    fullWidth
                    value={eventData.coords.longitude}
                    onChange={e => handleChange('coords.longitude', e.target.value)}
                    sx={{ marginBottom: 2 }}
                />
                <StyledTextField
                    label="Capacidad Máxima"
                    type="number"
                    variant="outlined"
                    fullWidth
                    value={eventData.eventMaxCapacity}
                    onChange={e => handleMaxCapacityChange(e.target.value)}
                    sx={{ marginBottom: 3 }}
                    error={capacityError}
                    helperText={capacityError ? 'La capacidad no puede ser negativa' : ''}
                />

                {/* Botón para crear el evento */}
                <Button
                    onClick={handleSubmit}
                    fullWidth
                    sx={{
                        backgroundColor: colors.primary,
                        color: 'white',
                        ':hover': {
                            backgroundColor: colors.secondary,
                        },
                    }}
                    disabled={capacityError || startDateError || endDateError}  // Desactivar el botón si hay error
                >
                    Crear Evento
                </Button>
            </Box>
        </Modal>
    );
};

export default CreateEventModal;
