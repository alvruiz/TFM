import { StyledEngineProvider } from "@mui/material";
import { useParams } from "react-router-dom";
import Header from "../header/Header";
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from "moment";
import { useEffect, useState } from "react";
import Grid from "@mui/material/Grid2";
import "react-big-calendar/lib/css/react-big-calendar.css";
import EventMap from "../map/events/EventMap";
import useVillageStore from "../../stores/village-store";
import { Card, CardContent } from '@mui/material';
import { ClipLoader } from "react-spinners";
import { Root } from "../list/MainPageStyles";
import { StyledTypographySubtitle, StyledTypographyTitle } from "./IndividualVillageStyles";
import colors from "../../utils/colors";
import EventDetailsModal from "./modal/EventDetailsModal";
import FestivityEvent from "../../model/Event";
import useModalStore from "../../stores/modal-store";
const IndividualVillage = () => {
    const id = useParams().id;
    const [calendarStartDate, setCalendarStartDate] = useState(null);
    const { openModal, setOpenModal, selectedEvent, setSelectedEvent } = useModalStore();

    const { getEvents, getVillage, village, events } = useVillageStore();
    useEffect(() => {
        setOpenModal(false);
        const fetchVillage = async () => {
            await getVillage(id);
            if (village && village.festivity) {
                setCalendarStartDate(new Date(village.festivity.startDate));
            }
        };
        fetchVillage();
    }, [id]);
    useEffect(() => {
        const fetchEvents = async () => {
            if (village === null) return;
            await getEvents(village.festivity.id);
            if (village && village.festivity && !calendarStartDate) {
                setCalendarStartDate(new Date(village.festivity.startDate));
            }
        };
        fetchEvents();
    }, [village]);


    useEffect(() => {
        console.log("hola")
    }, [events]);
    if (calendarStartDate === null) return null;
    const localizer = momentLocalizer(moment);
    if (!village || !events) {
        return <ClipLoader color="#6A4A3C" loading={true} size={70} />;
    }
    const handleEventClick = async (event) => {
        await getEvents(village.festivity.id);
        setSelectedEvent(event);
        setOpenModal(true);
    };

    const handleCloseModal = () => {

        setOpenModal(false);
        setSelectedEvent(null);
    };
    const defaultDate = "2025-08-19";
    const eventStyleGetter = (event, start, end, isSelected) => {
        var style = {
            backgroundColor: colors.secondary,
            borderRadius: '0px',
            opacity: 0.8,
            color: colors.textDark,
            border: '0px',
            display: 'block'
        };
        return {
            style: style
        };
    }
    return (
        <StyledEngineProvider injectFirst>
            <Root>
                <Header />
                <Grid container sx={{ flexGrow: 1, height: 'calc(100vh - 64px)', margin: 0, padding: 0 }}>
                    <Grid size={{ xs: 12 }} sx={{ padding: 0 }}>
                        <Card style={{ border: "none", boxShadow: "none", padding: 0 }}>
                            <CardContent sx={{ backgroundImage: `url(${village?.imageUrl ?? ""})`, backgroundSize: "cover", backgroundPosition: "center" }}>
                                <div style={{ width: "100%", backgroundColor: colors.backgroundLight, padding: 10 }}>
                                    <StyledTypographyTitle>
                                        {village?.name ?? ""} - {village?.festivity?.name ?? ""}
                                    </StyledTypographyTitle>
                                    <StyledTypographySubtitle variant="body2" color="text.secondary">
                                    </StyledTypographySubtitle>
                                    <StyledTypographySubtitle variant="body2" color="text.secondary">
                                        Fecha de inicio: {village?.festivity?.startDate ?? ""} - Fecha de fin: {village?.festivity?.endDate ?? ""}
                                    </StyledTypographySubtitle>
                                </div>
                            </CardContent>
                        </Card>
                    </Grid>

                    <Grid container sx={{ flexGrow: 1, height: 'calc(100vh - 10vh - 64px)', padding: 0 }}>

                        <Grid
                            size={{ xs: 12, sm: 12, md: 6, lg: 7 }}
                            order={{ xs: 1, sm: 1, md: 2, lg: 2 }}
                            sx={{ padding: 2, backgroundColor: colors.backgroundLight2, height: '100%' }}
                        >
                            <div style={{ height: '100%' }}>
                                <Calendar
                                    localizer={localizer}
                                    events={events && events.length > 0 ? events.map((event: FestivityEvent) => ({
                                        id: event.id || 'asdasd',
                                        title: event.eventName,
                                        start: event.eventStartDate ? new Date(event.eventStartDate) : new Date(),
                                        end: event.eventEndDate ? new Date(event.eventEndDate) : new Date()
                                    })) : []}
                                    showMultiDayTimes
                                    defaultDate={calendarStartDate || defaultDate}
                                    eventPropGetter={(eventStyleGetter)}

                                    style={{ height: '100%' }}
                                    onSelectEvent={async (event) => await handleEventClick(events.find(event2 => event.id === event2.id))}

                                />
                            </div>
                        </Grid>

                        <Grid
                            size={{ xs: 12, sm: 12, md: 6, lg: 5 }}
                            order={{ xs: 2, sm: 2, md: 1, lg: 1 }}
                            sx={{ height: '100%', padding: 0 }}
                        >
                            <div style={{ height: '100%', backgroundColor: colors.backgroundLight2, display: 'flex', flexDirection: 'column' }}>
                                {/* Event Map */}
                                <div style={{ flex: 1 }}>
                                    <EventMap village={village} events={events} />
                                </div>
                            </div>
                        </Grid>

                    </Grid>

                </Grid>
                {openModal &&
                    <EventDetailsModal
                        open={openModal}
                        selectedEvent={selectedEvent}
                        onClose={handleCloseModal}
                        village={village}
                    />}
            </Root>
        </StyledEngineProvider>
    );
};

export default IndividualVillage;
