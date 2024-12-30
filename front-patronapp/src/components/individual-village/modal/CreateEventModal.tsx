import React, { useState, useEffect } from 'react';
import { Modal, Box, Typography, IconButton, TextField, Button } from '@mui/material';
import { Close, Delete } from '@mui/icons-material';
import colors from '../../../utils/colors';
import { StyledTextField } from './CreateEventModalStyles';
import { toast } from 'react-toastify';
import useVillageStore from '../../../stores/village-store';

const CreateEventModal = ({ open, onClose, onCreate }) => {
    const [eventData, setEventData] = useState({
        eventName: '',
        eventDescription: '',
        eventStartDate: '',
        eventEndDate: '',
        coords: [],
        eventMaxCapacity: '',
    });

    const [errors, setErrors] = useState({
        eventName: false,
        eventDescription: false,
        eventStartDate: false,
        eventEndDate: false,
        coords: false,
        eventMaxCapacity: false,
    });

    const { village } = useVillageStore();

    useEffect(() => {
        if (village?.festivity?.startDate) {
            const startDate = new Date(village.festivity.startDate);
            const endDate = new Date(startDate);
            endDate.setDate(startDate.getDate() + 1); // Por ejemplo, un día después como fecha de fin

            const formattedStartDate = startDate.toISOString().slice(0, 16); // Formateo para datetime-local
            const formattedEndDate = endDate.toISOString().slice(0, 16);

            setEventData(prev => ({
                ...prev,
                eventStartDate: formattedStartDate,
                eventEndDate: formattedEndDate,
            }));
        }
    }, [village]);

    useEffect(() => {
        if (!open) {
            setEventData(prev => ({
                ...prev,
                eventName: '',
                eventDescription: '',
                coords: [],
                eventMaxCapacity: '',
            }));
            setErrors({
                eventName: false,
                eventDescription: false,
                eventStartDate: false,
                eventEndDate: false,
                coords: false,
                eventMaxCapacity: false,
            });
        }
    }, [open]);

    const validateFields = () => {
        const newErrors = {
            eventName: !eventData.eventName.trim(),
            eventDescription: !eventData.eventDescription.trim(),
            eventStartDate: !eventData.eventStartDate,
            eventEndDate: !eventData.eventEndDate || eventData.eventEndDate <= eventData.eventStartDate,
            coords: eventData.coords.length === 0 || eventData.coords.some(coord => !coord.latitude || !coord.longitude),
            eventMaxCapacity: !eventData.eventMaxCapacity || Number(eventData.eventMaxCapacity) < 1,
        };

        setErrors(newErrors);
        return Object.values(newErrors).every(error => !error);
    };

    const handleChange = (field, value) => {
        setEventData(prev => ({ ...prev, [field]: value }));
    };

    const handleAddCoord = () => {
        setEventData(prev => ({
            ...prev,
            coords: [...prev.coords, { latitude: '', longitude: '' }],
        }));
    };

    const handleRemoveCoord = index => {
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
        if (validateFields()) {
            onCreate(eventData);
            onClose();
        } else {
            toast.error('Por favor, completa todos los campos obligatorios.');
        }
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
                    error={errors.eventName}
                    helperText={errors.eventName ? 'El nombre del evento es obligatorio.' : ''}
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
                    error={errors.eventDescription}
                    helperText={errors.eventDescription ? 'La descripción es obligatoria.' : ''}
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
                    error={errors.eventStartDate}
                    helperText={errors.eventStartDate ? 'La fecha de inicio es obligatoria.' : ''}
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
                    error={errors.eventEndDate}
                    helperText={errors.eventEndDate ? 'La fecha de fin debe ser posterior a la fecha de inicio.' : ''}
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
                            error={!coord.latitude}
                        />
                        <StyledTextField
                            label="Longitud"
                            variant="outlined"
                            fullWidth
                            value={coord.longitude}
                            onChange={e => handleCoordChange(index, 'longitude', e.target.value)}
                            error={!coord.longitude}
                        />
                        <Button
                            onClick={() => handleRemoveCoord(index)}
                            sx={{ backgroundColor: colors.primary, color: 'white', ':hover': { backgroundColor: colors.secondary } }}
                        >
                            <Delete />
                        </Button>
                    </Box>
                ))}
                {errors.coords && <Typography color="error">Se requiere al menos una coordenada válida.</Typography>}
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
                    onChange={e => handleChange('eventMaxCapacity', e.target.value)}
                    sx={{ marginBottom: 3 }}
                    error={errors.eventMaxCapacity}
                    helperText={errors.eventMaxCapacity ? 'La capacidad debe ser mayor a 0.' : ''}
                />
                <Button
                    onClick={handleSubmit}
                    fullWidth
                    sx={{
                        backgroundColor: colors.primary,
                        color: 'white',
                        ':hover': { backgroundColor: colors.secondary },
                    }}
                >
                    Crear Evento
                </Button>
            </Box>
        </Modal>
    );
};

export default CreateEventModal;
