/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package dto;

import java.util.Date;

import models.Player;

/**
 * DTO con información del goleador.
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
public class StrikerDTO {

    public Player player;
    public long goals;
    public Date lastGoal;
    
    /** Creates the StrikerDTO. */
    public StrikerDTO(Player player, long goals, Date lastGoal) {
        this.player = player;
        this.goals = goals;
        this.lastGoal = lastGoal;
    }
    
}
