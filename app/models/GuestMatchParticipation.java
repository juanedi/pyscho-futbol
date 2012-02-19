/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Participación de un invitado en un partido. 
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@DiscriminatorValue("GUEST")
public class GuestMatchParticipation extends MatchParticipation {

    public String guestName;
    
    /** @see models.MatchParticipation#getPlayerDisplayName() */
    @Override
    public String getPlayerDisplayName() {
        return guestName;
    }

}
