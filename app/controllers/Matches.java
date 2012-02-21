/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OptimisticLock;

import models.GuestMatchParticipation;
import models.Match;
import models.MatchParticipation;
import models.Player;
import models.RegularMatchParticipation;
import models.Venue;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.mvc.Http;
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
        if (match == null) {
            response.status = Http.StatusCode.NOT_FOUND;
        } else {
            render(match);
        }
    }
    
    public static void newMatch() {
        List<Venue> venues = Venue.all().fetch();
        render(venues);
    }

    public static void postMatch(@Required final Date date, @Required final Long venueId) {
        if(validation.hasErrors()) {
            flash.error("Todos los campos son requeridos");
            newMatch();
        }
        Venue venue = Venue.findById(venueId);
        Match match = new Match();
        match.date = date;
        match.finished = false;
        match.venue = venue;
        match.save();
        detail(match.id);
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

    public static void setTeam(final Long matchId, final Long participationId, final String team) {
        MatchParticipation participation = MatchParticipation.findById(participationId);
        if (participation != null) {
            if (team.equals("null")) {
                participation.teamA = null;
            } else {
                participation.teamA = team.equals("A") ? true : !team.equals("B");
            }
            participation.save();
            response.status = Http.StatusCode.ACCEPTED;
        } else {
            response.status = Http.StatusCode.BAD_REQUEST;
        }
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
        
        flash.success("Listo! Participás del partido");
        detail(matchId);
    }
    
    public static void leave(final Long matchId) {
        final String username = Security.connected();
        MatchParticipation participation = 
            RegularMatchParticipation.find("match.id = ?1 and player.username = ?2", matchId, username).first();
        removeParticipation(matchId, participation);
        Application.index();        
    }
    
    public static void addGuest(final Long matchId, final String name) {
        GuestMatchParticipation participation = new GuestMatchParticipation();
        participation.guestName = name;
        addParticipation(matchId, participation);
        detail(matchId);
    }
    
    public static void removeGuest(final Long matchId, final Long participationId) {
        GuestMatchParticipation participation = GuestMatchParticipation.findById(participationId);
        removeParticipation(matchId, participation);
        detail(matchId);
    }
    
    private static void addParticipation(final Long matchId, final MatchParticipation participation) {
        Match match = Match.findById(matchId);
        match.participations.add(participation);
        participation.match = match;
        match.save();
        participation.save();
    }
    
    private static void removeParticipation(final Long matchId, final MatchParticipation participation) {
        Match match = Match.findById(matchId);
        if (match != null && participation != null) {
            match.participations.remove(participation);
            participation.delete();
        } else {
            response.status = Http.StatusCode.BAD_REQUEST;
        }
    }
}
