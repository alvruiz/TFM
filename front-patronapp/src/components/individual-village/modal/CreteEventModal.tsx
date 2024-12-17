import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, IconButton, TextField, Button } from '@mui/material';
import { Close, Delete } from '@mui/icons-material';
import colors from '../../../utils/colors';
import { StyledTextField } from './CreateEventModalStyles';

const CreateEventModal = ({ open, onClose, onCreate }) => {
    const [eventData, setEventData] = useState({
        eventName: '',
        eventDescription: '',
        eventStartDate: '',
        eventEndDate: '',
        coords: [], // Cambiado para soportar múltiples coordenadas
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
                coords: [],
                eventMaxCapacity: '',
            });
            setCapacityError(false);
            setStartDateError(false);
            setEndDateError(false);
        }
    }, [open]);

    const handleChange = (field, value) => {
        setEventData(prev => ({
            ...prev,
            [field]: value,
        }));
    };

    const handleMaxCapacityChange = (value) => {
        if (value < 0) {
            setCapacityError(true); // Mostrar error si el valor es negativo
        } else {
            setCapacityError(false); // Quitar error si el valor es válido
        }
        setEventData(prev => ({
            ...prev,
            eventMaxCapacity: value,
        }));
    };

    const handleDateValidation = () => {
        const today = new Date().toISOString().slice(0, 16);
        const startDateValid = eventData.eventStartDate >= today;
        const endDateValid = eventData.eventEndDate > eventData.eventStartDate;

        setStartDateError(eventData.eventStartDate && !startDateValid);
        setEndDateError(eventData.eventEndDate && !endDateValid);

        return startDateValid && endDateValid;
    };

    useEffect(() => {
        handleDateValidation();
    }, [eventData]);

    // Manejo dinámico de coordenadas
    const handleAddCoord = () => {
        setEventData(prev => ({
            ...prev,
            coords: [...prev.coords, { latitude: '', longitude: '' }], // Agregar coordenada vacía
        }));
    };

    const handleRemoveCoord = (index) => {
        setEventData(prev => ({
            ...prev,
            coords: prev.coords.filter((_, i) => i !== index),
        }));
    };

    const handleCoordChange = (index, field, value) => {
        setEventData(prev => ({
            ...prev,
            coords: prev.coords.map((coord, i) =>
                i === index ? { ...coord, [field]: value } : coord
            ),
        }));
    };

    const handleSubmit = () => {
        if (capacityError || !handleDateValidation()) {
            return; // No enviar si hay errores
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

                <Typography variant="h5" sx={{ marginBottom: 3, fontSize: '24px', color: colors.textDark, fontWeight: 'bold' }}>
                    Crear Nuevo Evento
                </Typography>

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
                <Typography sx={{ marginBottom: 2 }}>Coordenadas:</Typography>
                {eventData.coords.map((coord, index) => (
                    <Box key={index} sx={{ display: 'flex', gap: 2, alignItems: 'center', marginBottom: 2 }}>
                        <StyledTextField
                            label="Latitud"
                            variant="outlined"
                            fullWidth
                            value={coord.latitude}
                            onChange={e => handleCoordChange(index, 'latitude', e.target.value)}
                        />
                        <StyledTextField
                            label="Longitud"
                            variant="outlined"
                            fullWidth
                            value={coord.longitude}
                            onChange={e => handleCoordChange(index, 'longitude', e.target.value)}
                        />
                        <Button
                            onClick={() => handleRemoveCoord(index)}
                            sx={{ backgroundColor: colors.primary, color: 'white', ':hover': { backgroundColor: colors.secondary } }}
                        >
                            <Delete></Delete>
                        </Button>
                    </Box>
                ))}
                <Button
                    onClick={handleAddCoord}
                    sx={{
                        backgroundColor: colors.primary,
                        color: 'white',
                        marginBottom: 3,
                        ':hover': { backgroundColor: colors.secondary },
                    }}
                >
                    Agregar Coordenada
                </Button>
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
                <Button
                    onClick={handleSubmit}
                    fullWidth
                    sx={{
                        backgroundColor: colors.primary,
                        color: 'white',
                        ':hover': { backgroundColor: colors.secondary },
                    }}
                    disabled={capacityError || startDateError || endDateError}
                >
                    Crear Evento
                </Button>
            </Box>
        </Modal>
    );
};

export default CreateEventModal;
