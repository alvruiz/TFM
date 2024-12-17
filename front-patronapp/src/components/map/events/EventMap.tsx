import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import colors from '../../../utils/colors';
import useModalStore from '../../../stores/modal-store';

const EventMap = ({ village, events }) => {
    const { openModal, setOpenModal, selectedEvent, setSelectedEvent } = useModalStore();

    useEffect(() => {
        if (!village || !events) return;

        // Extraer las coordenadas del centro del mapa desde village
        const { latitude, longitude } = village.coords;
        // Crear el mapa y centrarlo en las coordenadas de village
        const map = L.map("map", {
            center: [latitude, longitude], // Coordenadas del centro de la provincia
            zoom: 15.5, // Nivel de zoom
            maxBoundsViscosity: 1.0, // Evitar que se pueda mover fuera de los límites
            dragging: true, // Activar el desplazamiento del mapa
            scrollWheelZoom: true, // Activar zoom con rueda del ratón
            touchZoom: true, // Activar zoom táctil
            doubleClickZoom: true, // Activar zoom con doble clic
        });

        // Cargar el mapa base con OpenStreetMap
        L.tileLayer(
            "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
            {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
            }
        ).addTo(map);

        // Añadir marcadores o rutas para cada evento dependiendo de las coordenadas
        events.forEach(event => {
            // Verificar si event.coords está disponible y es un array
            if (event.coords && Array.isArray(event.coords)) {
                if (event.coords.length === 1) {
                    // Si solo hay una coordenada, la tratamos como un marcador
                    const { latitude, longitude } = event.coords[0];
                    const eventName = event.eventName;
                    const eventDescription = event.eventDescription;
                    const eventStartDate = new Date(event.eventStartDate).toLocaleString();
                    const eventEndDate = new Date(event.eventEndDate).toLocaleString();
                    const currentAttendees = event.attendees.length;
                    const maxCapacity = event.eventMaxCapacity;

                    const marker = L.circleMarker([latitude, longitude], {
                        radius: 15,
                        fillColor: colors.textDark,
                        color: '#FFFFFF',
                        weight: 2,
                        opacity: 1,
                        fillOpacity: 0.8
                    })
                        .addTo(map)
                        .bindPopup(` 
                            <b>${eventName}</b><br>
                            <strong>Descripción:</strong> ${eventDescription}<br>
                            <strong>Fecha de inicio:</strong> ${eventStartDate}<br>
                            <strong>Fecha de fin:</strong> ${eventEndDate}<br>
                            <strong>Asistentes:</strong> ${currentAttendees} / ${maxCapacity}<br>
                            <div style="text-align: center; margin-top: 10px;">
                                <button id="event-button-${event.eventName}" 
                                    style="
                                        background-color: #F4A259; 
                                        color: #6A4A3C; 
                                        padding: 10px 20px; 
                                        border: none; 
                                        border-radius: 5px; 
                                        cursor: pointer;
                                        transition: all 0.3s ease;
                                    "
                                    onmouseover="this.style.backgroundColor='#6A4A3C'; this.style.color='#F4A259';"
                                    onmouseout="this.style.backgroundColor='#F4A259'; this.style.color='#6A4A3C';">
                                    Abrir
                                </button>
                            </div>
                        `);

                    // Asociar el evento al botón dentro del popup
                    marker.on('popupopen', () => {
                        const button = document.getElementById(`event-button-${event.eventName}`);
                        if (button) {
                            button.onclick = () => handleEventClick(event);
                        }
                    });
                } else if (event.coords.length > 1) {
                    // Verificar que las coordenadas no estén vacías antes de crear la ruta
                    if (event.coords.every(coord => coord && coord.latitude && coord.longitude)) {
                        // Si hay más de una coordenada, tratamos esto como una ruta
                        const route = L.polyline(
                            event.coords.map(coord => [coord.latitude, coord.longitude]), // Asegurarse de usar las coordenadas correctamente
                            {
                                color: 'red',       // Color de la ruta
                                weight: 6,          // Grosor de la ruta
                                opacity: 0.8        // Opacidad de la ruta
                            }
                        ).addTo(map);

                        // Crear contenido del popup para la ruta, similar a los marcadores
                        const eventName = event.eventName;
                        const eventDescription = event.eventDescription;
                        const eventStartDate = new Date(event.eventStartDate).toLocaleString();
                        const eventEndDate = new Date(event.eventEndDate).toLocaleString();
                        const currentAttendees = event.attendees.length;
                        const maxCapacity = event.eventMaxCapacity;

                        route.on('click', () => {
                            route.bindPopup(`
                                <b>${eventName}</b><br>
                                <strong>Descripción:</strong> ${eventDescription}<br>
                                <strong>Fecha de inicio:</strong> ${eventStartDate}<br>
                                <strong>Fecha de fin:</strong> ${eventEndDate}<br>
                                <strong>Asistentes:</strong> ${currentAttendees} / ${maxCapacity}<br>
                                <div style="text-align: center; margin-top: 10px;">
                                    <button id="event-button-${event.eventName}" 
                                        style="
                                            background-color: #F4A259; 
                                            color: #6A4A3C; 
                                            padding: 10px 20px; 
                                            border: none; 
                                            border-radius: 5px; 
                                            cursor: pointer;
                                            transition: all 0.3s ease;
                                        "
                                        onmouseover="this.style.backgroundColor='#6A4A3C'; this.style.color='#F4A259';"
                                        onmouseout="this.style.backgroundColor='#F4A259'; this.style.color='#6A4A3C';">
                                        Abrir
                                    </button>
                                </div>
                            `).openPopup();

                            // Asociar el evento al botón dentro del popup
                            const button = document.getElementById(`event-button-${event.eventName}`);
                            if (button) {
                                button.onclick = () => handleEventClick(event);
                            }
                        });
                    } else {
                        console.warn(`Evento "${event.eventName}" tiene coordenadas inválidas para la ruta.`);
                    }
                }
            } else {
                console.warn(`Evento "${event.eventName}" no tiene coordenadas válidas.`);
            }
        });

        // Limpiar mapa cuando el componente se desmonte
        return () => {
            map.remove();
        };
    }, [village, events]);

    // Función que se ejecuta cuando se hace clic en "Abrir"
    const handleEventClick = (event) => {
        setSelectedEvent(event);  // Setea el evento seleccionado en el store
        setOpenModal(true);  // Abre el modal
    };

    return <div id="map" style={{ height: "100%", width: "100%" }}></div>;
};

export default EventMap;
