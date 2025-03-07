import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import colors from '../../../utils/colors';

const ProvinceMap = ({ actualProvince, actualVillages }) => {
    useEffect(() => {
        if (!actualProvince || !actualVillages) return;

        // Extraer las coordenadas del centro del mapa desde actualProvince
        const { latitude, longitude } = actualProvince.coords;

        // Crear el mapa y centrarlo en las coordenadas de actualProvince
        const map = L.map("map", {
            center: [latitude, longitude], // Coordenadas del centro de la provincia
            zoom: 9, // Nivel de zoom
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

        // Añadir marcadores para cada pueblo en actualVillages con el marcador estándar (círculo)
        actualVillages.forEach(village => {
            const { latitude, longitude } = village.coords; // Coordenadas del pueblo
            const name = village.name; // Nombre del pueblo

            // Usamos un marcador estándar de Leaflet
            L.circleMarker([latitude, longitude], {
                radius: 10,
                fillColor: colors.textDark,
                color: '#FFFFFF',
                weight: 2,
                opacity: 1,
                fillOpacity: 0.8
            })
                .addTo(map)
                .bindPopup(`<b>${name}</b><br>Lat: ${latitude}<br>Lng: ${longitude}`);
        });

        // Limpiar mapa cuando el componente se desmonte
        return () => {
            map.remove();
        };
    }, [actualProvince, actualVillages]); // Reactividad ante cambios en actualProvince o actualVillages

    return <div id="map" style={{ height: "40vh", width: "100%", borderRadius: 40 }}></div>;
};

export default ProvinceMap;
