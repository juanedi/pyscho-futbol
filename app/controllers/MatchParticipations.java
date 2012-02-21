/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import models.MatchParticipation;
import play.mvc.Http;

/**
 * Participations
 * 
 * 
 * @author Juan Edi
 * @since Feb 21, 2012
 */
public class MatchParticipations extends SecureController {

    public static void setTeam(final Long participationId, final Boolean teamA) {
        MatchParticipation participation = MatchParticipation.findById(participationId);
        if (participation != null && teamA != null) {
            participation.teamA = teamA;
            participation.save();
            response.status = Http.StatusCode.ACCEPTED;
        } else {
            response.status = Http.StatusCode.BAD_REQUEST;
        }
    }
    
    public static void clearTeam(final Long participationId) {
        MatchParticipation participation = MatchParticipation.findById(participationId);
        if (participation != null) {
            participation.teamA = null;
            participation.save();
            response.status = Http.StatusCode.ACCEPTED;
        } else {
            response.status = Http.StatusCode.BAD_REQUEST;
        }
    }
    
}
