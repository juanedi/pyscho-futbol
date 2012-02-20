/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    
    @OneToOne(cascade = CascadeType.PERSIST)
    public Venue venue;
    public boolean finished;
    public Integer goalsTeamA;
    public Integer goalsTeamB;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = CascadeType.ALL)
    public List<MatchParticipation> participations = new LinkedList<MatchParticipation>();

    public List<MatchParticipation> getParticipationsByDate() {
        Collections.sort(participations, new Comparator<MatchParticipation>() {
            @Override
            public int compare(MatchParticipation p1, MatchParticipation p2) {
                int ret = -1;
                if (p1.joinDate.after(p2.joinDate)) {
                    ret = 1;
                }
                return ret;
            }
        });
        return participations;
    }
    
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

    public MatchParticipation striker() {
        return MatchParticipation.find("match = ?1 order by goals desc", this).first();
    }
    
    public static Match nextMatch() {
        return Match.find("finished", false).first();
    }

    public static Match lastMatch() {
        return Match.find("finished = true order by date desc").first();
    }
    
}
