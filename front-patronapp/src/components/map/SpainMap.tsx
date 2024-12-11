import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import useProvinceStore from '../../stores/province-store';
import { ClipLoader } from 'react-spinners';

const SpainMap = () => {
    const { provinces, isLoading } = useProvinceStore();

    useEffect(() => {

        if (provinces.length === 0) {
            return;
        }
        // Crear el mapa y centrarlo en el centro de España
        const map = L.map("map", {
            center: [40.4637, -3.7492], // Coordenadas del centro de España
            zoom: 6, // Nivel de zoom
            maxBoundsViscosity: 1.0, // Evitar que se pueda mover fuera de los límites
            dragging: true, // Activar el desplazamiento del mapa
            scrollWheelZoom: true, // Activar zoom con rueda del ratón
            touchZoom: true, // Activar zoom táctil
            doubleClickZoom: true, // Activar zoom con doble clic
        });

        // Cargar el mapa base con un estilo más suave de CartoDB
        L.tileLayer(
            "https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png",
            {
                attribution:
                    '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, &copy; <a href="https://carto.com/attributions">CartoDB</a>',
            }
        ).addTo(map);

        // Obtener el archivo GeoJSON de todas las provincias de España
        fetch(
            "https://raw.githubusercontent.com/codeforamerica/click_that_hood/master/public/data/spain-provinces.geojson"
        )
            .then((response) => response.json())
            .then((data) => {
                // Función para asignar un color de la paleta proporcionada
                function getColorByAutonomousCommunity(autonomousCommunity: string): string {
                    const communityColors: { [key: string]: string } = {
                        "Galicia": "#D35D35",
                        "Cantabria": "#D35A15",
                        "Castilla-La Mancha": "#F4A259",
                        "Comunidad Valenciana": "#264653",
                        "Andalucía": "#8B5E3C",
                        "Castilla y León": "#6A4A3C",
                        "Extremadura": "#A3B18A",
                        "Cataluña": "#A3D9F0",
                        "País Vasco": "#AB83B1",
                        "Ceuta": "#FFB1D1",
                        "Madrid": "#C1A9F3",
                        "Illes Balears": "#F2F2F2",
                        "Aragón": "#9E3A3A",
                        "La Rioja": "#4E8B5B",
                        "Canarias": "#FFE156",
                        "Navarra": "#57A0D2"
                    };
                    return communityColors[autonomousCommunity] || "#B0C4DE";
                }

                function getProvinceByFeature(feature) {
                    const featureSpanish = feature.properties.name.includes("/") ? feature.properties.name.split("/")[1] : feature.properties.name;
                    const province = provinces.find(province => province.name === featureSpanish);
                    return province;
                }

                // Estilo para las provincias con colores de la paleta
                function style(feature) {
                    const province = getProvinceByFeature(feature)

                    return {
                        fillColor: getColorByAutonomousCommunity(province?.autonomousCommunity || "#B0C4DE"),
                        weight: 1,
                        opacity: 1,
                        color: "white", // Borde blanco
                        dashArray: "3", // Línea discontinua
                        fillOpacity: 0.7,
                    };
                }

                // Función para agregar interactividad a cada provincia
                function onEachFeature(feature, layer) {
                    const province = getProvinceByFeature(feature)

                    const popupContent = `
                            <div style="text-align: center;">
                                <h3><strong>${province.name}</strong></h3>
                                <img src="${province.image}" alt="${feature.properties.name}" style="width: 100px; height: 100px; margin-bottom: 10px;" />
                                <button id="verMasBtn-${feature.properties.name}">Fiestas patronales</button>
                            </div>
                        `;
                    layer.bindPopup(popupContent);

                    layer.on('popupopen', () => {
                        const button = document.getElementById(`verMasBtn-${feature.properties.name}`);
                        if (button) {
                            button.onclick = () => {
                                alert(`Botón clickeado en ${feature.properties.name}!`);
                            };
                        }
                    });

                    layer.on({
                        mouseover: function (e) {
                            var layer = e.target;
                            layer.setStyle({
                                weight: 2,
                                color: "#ff0000",
                                fillColor: "#ff0000",
                                fillOpacity: 0.7,
                            });
                        },
                        mouseout: function (e) {
                            geojson.resetStyle(e.target);
                        },
                    });
                }

                const geojson = L.geoJSON(data, {
                    style: style,
                    onEachFeature: onEachFeature,
                }).addTo(map);
            })
            .catch((error) => {
                console.warn("Error cargando el GeoJSON: ", error);
            });

        // Limpiar mapa cuando el componente se desmonte
        return () => {
            map.remove();
        };
    }, [provinces]);

    return (
        <>
            {isLoading ? (
                <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                    <ClipLoader color="#6A4A3C" loading={isLoading} size={70} />
                </div>
            ) : (
                <div id="map" style={{ height: "100vh", width: "100%" }}></div>
            )}
        </>
    );
};

export default SpainMap;
