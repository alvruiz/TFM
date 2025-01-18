import moment from 'moment';
import { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import useEventStore from '../../stores/event-store';
import colors from '../../utils/colors';
import useModalStore from '../../stores/modal-store';
import EventDetailsModal from '../individual-village/modal/EventDetailsModal';
import FestivityEvent from '../../model/Event';
import useUserStore from '../../stores/user-store';
import { Root } from '../list/MainPageStyles';
import Header from '../header/Header';
import { StyledButton } from '../editprofile/EditProfileStyles';
import { CalendarMonth } from '@mui/icons-material';

function download(filename, fileBody) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(fileBody));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}
function createDownloadICSFile(events, timezone) {
    let icsBody = 'BEGIN:VCALENDAR\n' +
        'VERSION:2.0\n' +
        'PRODID:-//bobbin v0.1//NONSGML iCal Writer//EN\n' +
        'CALSCALE:GREGORIAN\n' +
        'METHOD:PUBLISH\n';

    events.forEach(event => {
        const startDate = moment(event.eventStartDate);
        const endDate = moment(event.eventEndDate);

        icsBody += 'BEGIN:VEVENT\n' +
            'DTSTART:' + convertToICSDate(startDate.toDate()) + 'Z\n' + // UTC time
            'DTEND:' + convertToICSDate(endDate.toDate()) + 'Z\n' + // UTC time
            'DTSTAMP:' + convertToICSDate(new Date()) + 'Z\n' +
            'UID:' + event.id + '@yoursever.com\n' +
            'CREATED:' + convertToICSDate(new Date()) + 'Z\n' +
            'DESCRIPTION:' + event.eventDescription + '\n' +
            'LAST-MODIFIED:' + convertToICSDate(new Date()) + 'Z\n' +
            'SEQUENCE:0\n' +
            'STATUS:CONFIRMED\n' +
            'SUMMARY:' + event.eventName + '\n' +
            'TRANSP:OPAQUE\n' +
            'END:VEVENT\n';
    });

    icsBody += 'END:VCALENDAR\n';

    download('events.ics', icsBody);
}

function convertToICSDate(dateTime) {
    const year = dateTime.getFullYear().toString();
    const month = (dateTime.getMonth() + 1) < 10 ? "0" + (dateTime.getMonth() + 1).toString() : (dateTime.getMonth() + 1).toString();
    const day = dateTime.getDate() < 10 ? "0" + dateTime.getDate().toString() : dateTime.getDate().toString();
    const hours = dateTime.getHours() < 10 ? "0" + dateTime.getHours().toString() : dateTime.getHours().toString();
    const minutes = dateTime.getMinutes() < 10 ? "0" + dateTime.getMinutes().toString() : dateTime.getMinutes().toString();

    return year + month + day + "T" + hours + minutes + "00";
}

export default function Agenda() {
    const localizer = momentLocalizer(moment);
    const { userEvents, getUserEvents } = useEventStore();
    const { user } = useUserStore();
    const { openModal, setOpenModal, selectedEvent, setSelectedEvent } = useModalStore();
    const [selectedVillage, setSelectedVillage] = useState(null);
    useEffect(() => {
        if (userEvents.length === 0) {
            setOpenModal(false);
            if (!user) return;

            getUserEvents(user.eventsParticipating);
        }
    }, [])
    useEffect(() => {
        if (!user) return;

    }, [user, userEvents]);

    useEffect(() => {
        getUserEvents(user.eventsParticipating);

    }, [user])

    if (userEvents === null) return null;
    if (!user) return null;

    const handleEventClick = (event) => {
        console.log(event)
        if (event && event.village) {
            setSelectedVillage(event.village);
            setSelectedEvent(event);
            setOpenModal(true);
        } else {
            console.error("Event is invalid", event);
        }
    };

    const handleCloseModal = () => {
        setOpenModal(false);
        setSelectedVillage(null);
        setSelectedEvent(null);
    };

    const exportEventsToICS = () => {
        const timezone = "Europe/Madrid";
        createDownloadICSFile(userEvents, timezone);
    };


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
    return (<>
        <Root>
            <Header></Header>
            <div style={{ height: '100%', padding: 10, backgroundColor: colors.backgroundLight2, display: 'flex', flexDirection: 'column' }}>
                <StyledButton style={{ marginBottom: 20, padding: 5, fontStyle: 'normal', display: 'flex', alignItems: 'center', justifyContent: 'center' }} onClick={exportEventsToICS}>
                    <CalendarMonth style={{ marginRight: 8 }} /> {/* Opcionalmente agrega un margen entre el icono y el texto */}
                    <span style={{ height: '100%', display: 'flex', alignItems: 'center' }}>Exportar Eventos</span>
                </StyledButton>
                <Calendar
                    localizer={localizer}
                    events={userEvents && userEvents.length > 0 ? userEvents.map((event: FestivityEvent) => ({
                        id: event.id || 'asdasd',
                        title: event.eventName,
                        start: event.eventStartDate ? new Date(event.eventStartDate) : new Date(),
                        end: event.eventEndDate ? new Date(event.eventEndDate) : new Date()
                    })) : []}
                    showMultiDayTimes
                    defaultDate={new Date()}
                    eventPropGetter={(eventStyleGetter)}

                    style={{ height: '100vh' }}
                    onSelectEvent={(event) => handleEventClick(userEvents.find(event2 => event.id === event2.id))}

                />
            </div>
            <EventDetailsModal
                open={openModal && selectedEvent !== null}
                selectedEvent={selectedEvent ? selectedEvent : { id: "1", eventName: "Evento no seleccionado", eventStartDate: new Date(), eventEndDate: new Date(), attendees: [], eventMaxCapacity: 0, coords: { latitude: 0, longitude: 0 } }}
                onClose={handleCloseModal}
                village={selectedVillage ? selectedVillage : { id: "1", name: "Villa no seleccionado", festivity: { name: "Festividad no seleccionado", startDate: new Date(), endDate: new Date() }, imageUrl: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.google.com&psig=AOvVaw2-8-4-1-7-3-0-2-5-1&ust=1658082328972000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJDr4n8z4oCFQAAAAAdAAAAABAD", events: [] }}
            />
        </Root>
    </>
    );
}