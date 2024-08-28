package org.forkingaround.adventuretime.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.forkingaround.adventuretime.dtos.ParticipantDto;
import org.forkingaround.adventuretime.exceptions.ParticipantException;
import org.forkingaround.adventuretime.exceptions.ParticipantNotFoundException;
import org.forkingaround.adventuretime.models.Participant;
import org.forkingaround.adventuretime.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    private ParticipantDto convertToDto(Participant participant) {
        return new ParticipantDto(
                participant.getId(), 
                participant.getJoinedAt(),
                participant.getEvent().getId(),
                participant.getUser() == null ? null : participant.getUser().getId()
                
                // participant.getEventUser()
        );
    }

    public List<ParticipantDto> getAllParticipants() {
        return participantRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public Optional<ParticipantDto> getParticipantById(Long id) {
        return participantRepository.findById(id).map(this::convertToDto);
    }

    public Participant addParticipant(ParticipantDto participantDto) {
        Long userId = participantDto.getUserId();
        if (userId == null) {
            throw new ParticipantException("UserId cannot be null");
        }

        Participant participant = new Participant();
        participant.setJoinedAt(participantDto.getJoinedAt());
        participant.setEvent(participantRepository.findById(participantDto.getId())
                .orElseThrow(() -> new ParticipantException("Event with id " + participantDto.getId() + " not found")));
        participant.setUser(new User(userId));

        return participantRepository.save(participant);
    }

    private static class User {
        User(Long userId) {
            this.userId = userId;
        }

        private final Long userId;
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
       