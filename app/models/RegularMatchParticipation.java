/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @OneToOne
    public Player player;

    /** @see MatchParticipation#getPlayerDisplayName() */
    @Override
    public String getPlayerDisplayName() {
        return player.username;
    }

    public static long playedGames(final Player player) {
        return RegularMatchParticipation.count("byPlayer", player);
    }
    
}
