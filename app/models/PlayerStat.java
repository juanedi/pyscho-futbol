/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

/**
 * Estadísticas de un jugador
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
@Entity
@Table(name = "player_stat")
public class PlayerStat extends Model {

    @OneToOne(optional = false)
    public Player player;
    
    public long victories;
    public long draws;
    public long defeats;
    public long goals;
    
    public static PlayerStat forPlayer(final Player player) {
        return PlayerStat.find("byPlayer", player).first();
    }

    public static PlayerStat striker() {
        return PlayerStat.find("order by goals desc").first();    
    }
    
    public static PlayerStat mvp() {
        return PlayerStat.find("order by victories desc").first();
    }
    
    public static PlayerStat lvp() {
        return PlayerStat.find("order by victories asc").first();
    }
}
