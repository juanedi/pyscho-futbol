/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
import play.libs.Crypto;
import dto.StrikerDTO;

/**
 * Jugador
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@Entity
@Table(name = "players")
public class Player extends Model {

    public static String STRIKER_QUERY = 
        "select new dto.StrikerDTO(p.player, sum(p.goals), max(p.match.date)) from RegularMatchParticipation p " 
        + "group by p.player order by sum(p.goals) desc";
    
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    
    public String getPassword() {
        return Crypto.decryptAES(this.password);
    }
    
    public void setPassword(final String password) {
        this.password = Crypto.encryptAES(password);
    }

    public static StrikerDTO allTimeStriker() {
        return Player.find(STRIKER_QUERY).first();
    }
    
}
