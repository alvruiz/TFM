import moment from 'moment';
import { useEffect } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import useEventStore from '../../stores/event-store';
import colors from '../../utils/colors';
import useModalStore from '../../stores/modal-store';
import EventDetailsModal from '../individual-village/modal/EventDetailsModal';
import useVillageStore from '../../stores/village-store';
import FestivityEvent from '../../model/Event';
import useUserStore from '../../stores/user-store';
import { Root } from '../list/MainPageStyles';
import Header from '../header/Header';


export default function Agenda() {
    const localizer = momentLocalizer(moment);
    const { userEvents, getUserEvents } = useEventStore();
    const { getPersistedUser, user } = useUserStore();
    const { openModal, setOpenModal, selectedEvent, setSelectedEvent, selectedVillage, setSelectedVillage } = useModalStore();
    const { getVillage } = useVillageStore();
    useEffect(() => {
        if (userEvents.length === 0) {
            setOpenModal(false);
            getPersistedUser();
            if (!user) return;

            getUserEvents(user.eventsParticipating);
        }
    }, [])
    useEffect(() => {
        if (!user) return;  // Si no hay usuario, no hacemos nada.

        if (userEvents.length === 0) {
            setOpenModal(false);
            getUserEvents(user.eventsParticipating);
        }
    }, [user, userEvents]);  // Aquí agregamos 'user' y 'userEvents' como dependencias

    if (userEvents === null) return null;
    if (!user) return null;

    const handleEventClick = (event) => {
        if (event && event.village) {
            getVillage(event.village.id);
            setSelectedVillage(event.village);
            setSelectedEvent(event);
            console.log(event);
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
                event={selectedEvent ? selectedEvent : { id: "1", eventName: "Evento no seleccionado", eventStartDate: new Date(), eventEndDate: new Date(), attendees: [], eventMaxCapacity: 0, coords: { latitude: 0, longitude: 0 } }}
                onClose={handleCloseModal}
                village={selectedVillage ? selectedVillage : { id: "1", name: "Villa no seleccionado", festivity: { name: "Festividad no seleccionado", startDate: new Date(), endDate: new Date() }, imageUrl: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.google.com&psig=AOvVaw2-8-4-1-7-3-0-2-5-1&ust=1658082328972000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJDr4n8z4oCFQAAAAAdAAAAABAD", events: [] }}
            />
        </Root>
    </>
    );
}