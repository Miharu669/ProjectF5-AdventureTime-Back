package org.forkingaround.adventuretime.exceptions;

public class ParticipantException extends RuntimeException{
    
    public ParticipantException(String message) {
        super(message);
    }

    public ParticipantException(String message, Throwable cause) {
        super(message, cause);
    }

}
