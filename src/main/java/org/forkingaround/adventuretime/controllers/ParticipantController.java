package org.forkingaround.adventuretime.controllers;

import java.util.List;

import org.forkingaround.adventuretime.dtos.ParticipantDto;
import org.forkingaround.adventuretime.models.Participant;
import org.forkingaround.adventuretime.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api-endpoint}/participant")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class ParticipantController {
    
    @Autowired
    private ParticipantService participantService;
    @PostMapping
    public ResponseEntity<Participant> addParticipant(ParticipantDto participantDto) {
        return ResponseEntity.ok(participantService.addParticipant(participantDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParticipantDto>> getAllParticipants() {
        try {
            List<ParticipantDto> participants = participantService.getAllParticipants();
            return ResponseEntity.ok(participantService.convertToDtos(participants));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
