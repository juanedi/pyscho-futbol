/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Partido
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@Table(name = "matches")
public class Match extends Model {

    public Date date;
    public Venue venue;
    public boolean finished;
    public Integer goalsTeamA;
    public Integer goalsTeamB;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match")
    public List<MatchParticipation> participations;

    public List<MatchParticipation> getPlayersTeamA() {
        return playersForTeam(true);
    }

    public List<MatchParticipation> getPlayersTeamB() {
        return playersForTeam(false);
    }
    
    private List<MatchParticipation> playersForTeam(Boolean teamA) {
       List<MatchParticipation> ret = new LinkedList<MatchParticipation>();
       for (MatchParticipation participation : participations) {
           if (teamA.equals(participation.teamA)) {
               ret.add(participation);
           }
       }
       return ret;
    }
    
}
