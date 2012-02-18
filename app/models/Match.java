/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Partido
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Table(name = "match")
public class Match extends Model {

    public Date date;
    public Venue venue;
    public boolean finished;
    public List<Player> playersTeamA;
    public List<Player> playersTeamB;
    
}
