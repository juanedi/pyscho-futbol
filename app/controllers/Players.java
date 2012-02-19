/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.List;

import models.Player;
import models.PlayerStat;
import models.RegularMatchParticipation;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;

/**
 * Controller para listado y detalle de jugadores
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
public class Players extends Controller {

    public static void list() {
        List<Object> playerStats = PlayerStat.all().fetch();
        render(playerStats);
    }
    
    public static void detail(final String username) {
        Player player = Player.find("byUsername", username).first();
        long playedGames = RegularMatchParticipation.playedGames(player);
        if (player == null) {
            response.status = 404;
        } else {
            render(player, playedGames);
        }
    }
    
}
