import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import colors from '../../../utils/colors';

const ModalMap = ({ event }) => {
    useEffect(() => {
        if (!event || !event.coords) return;

        // Extraer las coordenadas del evento
        const { latitude, longitude } = event.coords;

        // Crear el mapa y centrarlo en las coordenadas del evento
        const map = L.map("modal-map", {
            center: [latitude, longitude], // Coordenadas del evento
            zoom: 20, // Nivel de zoom más cercano para un solo evento
            dragging: false, // Desactivar el desplazamiento
            scrollWheelZoom: false, // Desactivar zoom con rueda del ratón
            touchZoom: false, // Desactivar zoom táctil
            doubleClickZoom: false, // Desactivar zoom con doble clic
        });

        // Cargar el mapa base con OpenStreetMap
        L.tileLayer(
            "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
            {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
            }
        ).addTo(map);

        // Añadir un marcador para el evento
        L.marker([latitude, longitude], {
            icon: L.icon({
                iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
                iconSize: [25, 41],
                iconAnchor: [12, 41],
                popupAnchor: [1, -34],
                shadowSize: [41, 41]
            })
        })
            .addTo(map)
            .bindPopup(`<b>${event.eventName}</b><br>Lat: ${latitude}<br>Lng: ${longitude}`);

        // Limpiar mapa cuando el componente se desmonte
        return () => {
            map.remove();
        };
    }, []); // Reactividad ante cambios en el evento

    return <div id="modal-map" style={{ height: "250px", width: "100%" }}></div>;
};

export default ModalMap;
