import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import colors from '../../../utils/colors';

const ModalMap = ({ event }) => {
    useEffect(() => {
        if (!event || !event.coords || event.coords.length === 0) return; // Ensure coords are available

        // Extraer las coordenadas del evento
        const coordinates = event.coords;

        // Safeguard to avoid destructuring error
        const { latitude, longitude } = coordinates[0] || {};

        if (!latitude || !longitude) return; // If there's no valid coordinates, return

        // Crear el mapa y centrarlo en la primera coordenada
        const map = L.map("modal-map", {
            center: [latitude, longitude], // Coordenadas del primer punto
            zoom: coordinates.length > 1 ? 14 : 18, // Si hay ruta, un poco menos de zoom
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

        if (coordinates.length === 1) {
            // Si hay solo una coordenada, se muestra un marcador
            L.marker([latitude, longitude], {
                icon: L.icon({
                    iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
                    iconSize: [25, 41],
                    iconAnchor: [12, 41],
                    popupAnchor: [1, -34],
                    shadowSize: [41, 41]
                })
            }).addTo(map); // Sin popup
        } else if (coordinates.length > 1) {
            // Si hay varias coordenadas, se dibuja una ruta
            L.polyline(
                coordinates.map(coord => [coord.latitude, coord.longitude]),
                {
                    color: 'red',  // Color de la ruta
                    weight: 6,     // Grosor de la ruta
                    opacity: 0.8   // Opacidad de la ruta
                }
            ).addTo(map);
        }

        // Limpiar mapa cuando el componente se desmonte
        return () => {
            map.remove();
        };
    }, [event]); // Reactividad ante cambios en el evento

    return <div id="modal-map" style={{ height: "250px", width: "100%" }}></div>;
};

export default ModalMap;
