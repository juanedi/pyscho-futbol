/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Cancha donde se juegan los partidos.
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Table(name = "venue")
public class Venue extends Model {

    public String name;
    public String address;
    
}
