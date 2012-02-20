/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.Model;

/**
 * Participación de un jugador en un partido
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@Table(name = "match_participations", uniqueConstraints = @UniqueConstraint(columnNames={"match_id", "guest_name"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MatchParticipation extends Model {

    @ManyToOne(optional = false)
    @JoinColumn(name = "match_id")
    public Match match;
    
    public Boolean teamA;
    public int goals;
    
    @Column(name = "join_date", nullable = false)
    public Date joinDate = new Date();
    
    public abstract String getPlayerDisplayName();
    public abstract boolean isGuest();

}
