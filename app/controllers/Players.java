/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.List;

import models.Player;
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
        List<Player> players = Player.all().fetch();
        render(players);
    }
    
}
