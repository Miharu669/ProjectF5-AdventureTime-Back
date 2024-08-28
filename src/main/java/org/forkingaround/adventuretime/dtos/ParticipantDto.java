package org.forkingaround.adventuretime.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantDto {
    private Long id;
    private LocalDateTime joinedAt;
}
