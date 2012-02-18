/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Jugador
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@Table(name = "player")
public class Player extends Model {

    public String username;
    public String password;
    public String firstName;
    public String lastName;

}
