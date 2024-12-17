package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event.EventMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Slf4j
public class EventLoader {

    @Bean
    public CommandLineRunner loadEvents(EventMongoRepository eventRepository) {
        return args -> {
            eventRepository.deleteAll();
            List<EventEntity> events = List.of(
                    EventEntity.builder().id("1").name("Concierto de Folk").description("Espectáculo musical al aire libre con artistas locales.").startDate(LocalDateTime.of(2025, 9, 19, 16, 0)).endDate(LocalDateTime.of(2025, 9, 19, 18, 0)).coords(List.of(Coords.builder().latitude(41.389710).longitude(-5.263901).build())).maxCapacity(150).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("2").name("Exposición de Artesanía").description("Muestra de productos locales hechos a mano.").startDate(LocalDateTime.of(2025, 9, 20, 10, 0)).endDate(LocalDateTime.of(2025, 9, 20, 13, 0)).coords(List.of(Coords.builder().latitude(41.389802).longitude(-5.263589).build())).maxCapacity(50).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("3").name("Degustación de Vinos").description("Evento gastronómico con cata de vinos de la región.").startDate(LocalDateTime.of(2025, 9, 20, 17, 0)).endDate(LocalDateTime.of(2025, 9, 20, 19, 0)).coords(List.of(Coords.builder().latitude(41.388940).longitude(-5.263813).build())).maxCapacity(75).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("4").name("Carrera Popular").description("Competición para corredores de todas las edades.").startDate(LocalDateTime.of(2025, 9, 21, 9, 0)).endDate(LocalDateTime.of(2025, 9, 21, 11, 0)).coords(List.of(Coords.builder().latitude(41.388701).longitude(-5.263379).build())).maxCapacity(200).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("5").name("Teatro al Aire Libre").description("Representación de una obra clásica en la plaza del pueblo.").startDate(LocalDateTime.of(2025, 9, 21, 18, 0)).endDate(LocalDateTime.of(2025, 9, 21, 20, 0)).coords(List.of(Coords.builder().latitude(41.388730).longitude(-5.262751).build())).maxCapacity(100).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("6").name("Concurso de Fotografía").description("Exposición y premiación de las mejores fotos locales.").startDate(LocalDateTime.of(2025, 9, 19, 16, 0)).endDate(LocalDateTime.of(2025, 9, 19, 18, 0)).coords(List.of(Coords.builder().latitude(41.388619).longitude(-5.262314).build())).maxCapacity(50).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("7").name("Taller de Cocina Tradicional").description("Aprende a preparar platos típicos de la región.").startDate(LocalDateTime.of(2025, 9, 20, 11, 0)).endDate(LocalDateTime.of(2025, 9, 20, 13, 30)).coords(List.of(Coords.builder().latitude(41.388635).longitude(-5.261608).build())).maxCapacity(30).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("8").name("Paseo Guiado por la Reserva Natural").description("Ruta a pie para conocer la flora y fauna de Castronuño.").startDate(LocalDateTime.of(2025, 9, 22, 9, 0)).endDate(LocalDateTime.of(2025, 9, 22, 11, 30)).coords(List.of(Coords.builder().latitude(41.389232).longitude(-5.210620).build())).maxCapacity(25).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("9").name("Cine Bajo las Estrellas").description("Proyección de una película en un espacio abierto.").startDate(LocalDateTime.of(2025, 9, 21, 21, 30)).endDate(LocalDateTime.of(2025, 9, 21, 23, 30)).coords(List.of(Coords.builder().latitude(41.388665).longitude(-5.260867).build())).maxCapacity(120).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("10").name("Feria Gastronómica").description("Un recorrido por los sabores típicos de Castronuño.").startDate(LocalDateTime.of(2025, 9, 22, 13, 0)).endDate(LocalDateTime.of(2025, 9, 22, 15, 0)).coords(List.of(Coords.builder().latitude(41.390141).longitude(-5.261502).build())).maxCapacity(100).attendees(List.of()).festivityId("1").build(),
                    EventEntity.builder().id("11").name("Corrida toros").description("La increible y tradicional corrida de toros de Castronuño.").startDate(LocalDateTime.of(2025, 9, 22, 13, 0)).endDate(LocalDateTime.of(2025, 9, 22, 15, 0)).coords(List.of(
                            Coords.builder().latitude(41.390354).longitude(-5.256962).build(),
                            Coords.builder().latitude(41.390101).longitude(-5.258039).build(),
                            Coords.builder().latitude(41.389560).longitude(-5.259122).build(),
                            Coords.builder().latitude(41.389232).longitude(-5.260680).build()
                    )).maxCapacity(100).attendees(List.of()).festivityId("1").build()
            );

            eventRepository.saveAll(events);
            log.info("Events loaded successfully");
        };
    }
}
