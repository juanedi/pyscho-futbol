/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.Date;
import java.util.List;

import models.GuestMatchParticipation;
import models.Match;
import models.MatchParticipation;
import models.Player;
import models.RegularMatchParticipation;
import models.Venue;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.mvc.Http.StatusCode;

/**
 * Listado y cargad de datos de partidos
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
public class Matches extends SecureController {

    public static void list() {
        List<Match> matches = Match.find("finished", true).fetch();
        render(matches);
    }

    public static void detail(final Long id) {
        Match match = Match.findById(id);
        render(match);
    }
    
    public static void newMatch() {
        List<Venue> venues = Venue.all().fetch();
        render(venues);
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

    public static void join(final Long matchId) {
        String username = Security.connected();
        Player player = Player.find("byUsername", username).first();
        Match match = Match.findById(matchId);
        RegularMatchParticipation participation = new RegularMatchParticipation();
        participation.player = player;
        match.participations.add(participation);
        participation.match = match;
        match.save();
        detail(matchId);
    }
    
    public static void leave(final Long matchId) {
        final String username = Security.connected();
        Match match = Match.findById(matchId);
        MatchParticipation participation = 
            RegularMatchParticipation.find("match.id = ?1 and player.username = ?2", matchId, username).first();
        match.participations.remove(participation);
        participation.delete();
        Application.index();        
    }
    
    public static void addGuest(final Long matchId, final String name) {
        GuestMatchParticipation participation = new GuestMatchParticipation();
        participation.guestName = name;
        addParticipation(matchId, participation);
        detail(matchId);
    }
    
    private static void addParticipation(final Long matchId, final MatchParticipation participation) {
        Match match = Match.findById(matchId);
        match.participations.add(participation);
        participation.match = match;
        match.save();
        participation.save();
    }
    
}
