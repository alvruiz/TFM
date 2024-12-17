import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import colors from '../../../utils/colors';
import useModalStore from '../../../stores/modal-store';

const EventMap = ({ village, events }) => {
    const { openModal, setOpenModal, selectedEvent, setSelectedEvent } = useModalStore();  // Usamos el store

    useEffect(() => {
        if (!village || !events) return;

        // Extraer las coordenadas del centro del mapa desde actualProvince
        const { latitude, longitude } = village.coords;
        // Crear el mapa y centrarlo en las coordenadas de actualProvince
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

        // Añadir marcadores para cada evento en events
        events.forEach(event => {
            const { latitude, longitude } = event.coords; // Coordenadas del evento
            const eventName = event.eventName; // Nombre del evento
            const eventDescription = event.eventDescription; // Descripción del evento
            const eventStartDate = new Date(event.eventStartDate).toLocaleString(); // Fecha de inicio
            const eventEndDate = new Date(event.eventEndDate).toLocaleString(); // Fecha de fin
            const currentAttendees = event.attendees.length; // Número de asistentes actuales
            const maxCapacity = event.eventMaxCapacity; // Capacidad máxima

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

            // Eliminar clic del marcador que abre el modal
            // Eliminamos esta línea para que no se abra el modal al hacer clic en el marcador
            // marker.on('click', () => {
            //    handleEventClick(event);
            // });
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
