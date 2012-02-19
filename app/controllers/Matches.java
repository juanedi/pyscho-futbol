/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.Date;
import java.util.List;

import models.Match;
import models.Venue;
import play.data.validation.Required;
import play.mvc.Controller;
import play.mvc.Http.StatusCode;

/**
 * Listado y cargad de datos de partidos
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
public class Matches extends Controller {

    public static void list() {
        render();
    }

    public static void input() {
        Match pendingMatch = Match.nextMatch();
        List<Venue> venues = Venue.all().fetch();
        render(pendingMatch, venues);
    }

    public static void postMatch(@Required final Date date, @Required final Long venueId) {
        Venue venue = Venue.findById(venueId);
        Match match = new Match();
        match.date = date;
        match.finished = false;
        match.venue = venue;
        match.save();
        Application.index();
    }

    public static void cancel(final Long matchId) {
        Match match = Match.findById(matchId);
        if (!match.finished) {
            match.delete();
        } else {
            response.status = StatusCode.BAD_REQUEST;
        }
        Application.index();
    }
    
}
