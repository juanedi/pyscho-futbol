/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "match")
public class Match extends Model {

    public Date date;
    public Venue venue;
    public boolean finished;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "match_player_team_a", 
               joinColumns = @JoinColumn(name = "match_id"), 
               inverseJoinColumns = @JoinColumn(name = "player_id"))
    public List<Player> playersTeamA;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "match_player_team_b",
               joinColumns = @JoinColumn(name = "match_id"), 
               inverseJoinColumns = @JoinColumn(name = "player_id"))
    public List<Player> playersTeamB;
    
}
