/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import models.Player;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Controller que requiere autenticación y setea el usuario.
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
@With(Secure.class)
public abstract class SecureController extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            Player player = Player.find("byUsername", Security.connected()).first();
            renderArgs.put("loggedPlayer", player);
        }
    }
    
}
