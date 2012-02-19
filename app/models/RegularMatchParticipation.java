/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Participación de un jugador registrado en un partido.
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@DiscriminatorValue("REGULAR")
public class RegularMatchParticipation extends MatchParticipation {

    public Player player;

    /** @see MatchParticipation#getPlayerDisplayName() */
    @Override
    public String getPlayerDisplayName() {
        return player.username;
    }
    
}
