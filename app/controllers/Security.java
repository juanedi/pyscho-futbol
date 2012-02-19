/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import models.Player;

/**
 * Maneja autenticación
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
public class Security extends controllers.Secure.Security {

    static boolean authenticate(final String username, final String password) {
        Player player = Player.find("byUsername", username).first();
        return player != null && player.password.equals(password);
    }
    
}
