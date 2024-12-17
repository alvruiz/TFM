package com.viu.patronAPP.infrastructure.DTO.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubscribeDTO {
    private String email;
    private String eventId;
}
