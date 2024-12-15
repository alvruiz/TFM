import { StyledEngineProvider } from "@mui/material";
import { useParams } from "react-router-dom";
import Header from "../header/Header";
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from "moment";
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid2";
import "react-big-calendar/lib/css/react-big-calendar.css";
import EventMap from "../map/events/EventMap";
import useVillageStore from "../../stores/village-store";
import { Card, CardContent, CardMedia } from '@mui/material';
import { ClipLoader } from "react-spinners";
import { Root } from "../list/MainPageStyles";
import { StyledTypographySubtitle, StyledTypographyTitle } from "./IndividualVillageStyles";
import colors from "../../utils/colors";


const IndividualVillage = () => {
    const id = useParams().id;
    const [calendarStartDate, setCalendarStartDate] = useState(null);

    const { getEvents, getVillage, village, events } = useVillageStore();
    useEffect(() => {
        const fetchVillage = async () => {
            await getVillage(id);
        };
        fetchVillage();
    }, [id]);

    useEffect(() => {
        const fetchEvents = async () => {
            if (village === null) return;
            await getEvents(village.id);
        };
        fetchEvents();
    }, [village]);

    useEffect(() => {
        if (village && village.festivity && !calendarStartDate) {
            setCalendarStartDate(new Date(village.festivity.startDate));
        }
    }, [village, calendarStartDate]);


    const localizer = momentLocalizer(moment);
    if (!village || !events) {
        return <ClipLoader color="#6A4A3C" loading={true} size={70} />;
    }
    const isValidDate = (date) => {
        const parsedDate = new Date(date);
        return !isNaN(parsedDate.getTime());
    };

    const defaultDate = "2025-08-19";

    console.log(village)
    return (
        <StyledEngineProvider injectFirst>

            <Root>

                <Header></Header>
                <Grid container sx={{ flexGrow: 1 }}>
                    <Grid size={{ xs: 12, sm: 12, md: 12, lg: 12 }} sx={{ height: '12vh' }} >
                        <Card style={{ border: "none", boxShadow: "none" }}>

                            <CardContent sx={{ backgroundImage: `url(${village?.imageUrl ?? ""})`, backgroundSize: "cover", backgroundPosition: "center" }}>
                                <div style={{ width: "50%", backgroundColor: colors.backgroundLight, borderRadius: 10, padding: 10, marginBottom: 10 }}>
                                    <StyledTypographyTitle>
                                        {village?.name ?? ""} - {village?.festivity?.name ?? ""}
                                    </StyledTypographyTitle>
                                    <StyledTypographySubtitle variant="body2" color="text.secondary">
                                    </StyledTypographySubtitle>
                                    <StyledTypographySubtitle variant="body2" color="text.secondary">
                                        Ubicación: Latitud: {village?.coords?.latitude ?? ""}, Longitud: {village?.coords?.longitude ?? ""} <br></br>
                                        Fecha de inicio: {village?.festivity?.startDate ?? ""} - Fecha de fin: {village?.festivity?.endDate ?? ""}
                                    </StyledTypographySubtitle>
                                </div>
                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid size={{ xs: 12, sm: 12, md: 6, lg: 4 }} sx={{ flexGrow: 1, height: "81vh", paddingTop: 0.4 }} >
                        <EventMap
                            village={village}
                            events={events}
                        />

                    </Grid>
                    <Grid size={{ xs: 12, sm: 12, md: 6, lg: 8 }}>
                        <div style={{ height: "81vh", padding: 1 }}>
                            <Calendar
                                localizer={localizer}
                                events={events && events.length > 0 ? events.map((event) => ({
                                    title: event.eventName,
                                    start: event.eventStartDate ? new Date(event.eventStartDate) : new Date(),
                                    end: event.eventEndDate ? new Date(event.eventEndDate) : new Date()
                                })) : []}
                                date={calendarStartDate || defaultDate}
                                startAccessor="start"
                                endAccessor="end"
                                style={{ font: "Roboto" }}
                                onShowMore={(events, date) => console.log(date)}
                            />
                        </div>
                    </Grid>


                </Grid>
            </Root>
        </StyledEngineProvider >


    );
};

export default IndividualVillage;