import React, { useEffect } from 'react';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import useProvinceStore from '../../stores/province-store';
import { ClipLoader } from 'react-spinners';
import colors from '../../utils/colors';
import { useNavigate } from 'react-router-dom';

const SpainMap = () => {
    const { provinces, isLoading } = useProvinceStore();
    const navigate = useNavigate();

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

                function onEachFeature(feature, layer) {
                    const province = getProvinceByFeature(feature);
                    const popupContent = `
                <div 
                    style="text-align: center; display: flex; justify-content: center; align-items: center; flex-direction: column; background-image: url('${province.image}'); background-size: cover; background-position: center; color: white; padding: 20px; width: 250px; height: 300px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);"
                >
                    <div style="background-color: rgba(0, 0, 0, 0.7); border-radius: 10%; padding: 10px; margin-bottom: 10px;">
                        <h3 style="font-family: 'Roboto', sans-serif; color: white; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); margin: 0;">
                            <strong>${province.name}</strong>
                        </h3>
                    </div>
                    <button 
                        id="verMasBtn-${feature.properties.name}" 
                        data-province-id="${province.id}" 
                        style="background-color: #F4A259; color: #6A4A3C; border: none; padding: 10px 20px; cursor: pointer; font-family: 'Roboto', sans-serif; text-transform: none; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); border-radius: 5px; text-align: center; transition: background-color 0.3s ease;"
                        onmouseover="this.style.backgroundColor='#4C3B29'; this.style.color='white';"
                        onmouseout="this.style.backgroundColor='#F4A259'; this.style.color='#6A4A3C';"
                    >
                        Fiestas patronales
                    </button>
                </div>
                    `;

                    layer.bindPopup(popupContent);

                    layer.on('popupopen', () => {
                        const button = document.getElementById(`verMasBtn-${feature.properties.name}`);
                        if (button) {
                            button.onclick = () => {
                                const provinceId = button.getAttribute('data-province-id');
                                navigate(`/province/${provinceId}`);
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
                <div id="map" style={{ position: "absolute", height: "100vh", width: "100%" }}></div>
            )}
        </>
    );
};

export default SpainMap;
