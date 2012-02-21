/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import models.Player;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 * Registración
 * 
 * 
 * @author Juan Edi
 * @since Feb 20, 2012
 */
public class Register extends Controller {

    public static void registerForm() {
        render();
    }
    
    public static void register(@Required final String name, 
                                @Required final String lastName,
                                @Required final String username, 
                                @Required final String password, 
                                @Required final String passwordConfirmation) {
        Player player = Player.find("byUsername", username).first();
        
        if (validation.hasErrors()) {
            flash.error("Todos los campos son obligatorios");
            registerForm();
        }
        if (player != null) {
            flash.error("El usuario ya existe.");
            registerForm();
        }
        if (!password.equals(passwordConfirmation)) {
            flash.error("Los passwords no coinciden.");
            registerForm();            
        }
        
        Player newPlayer = new Player();
        newPlayer.firstName = name;
        newPlayer.lastName = lastName;
        newPlayer.username = username;
        newPlayer.password = password;
        newPlayer.save();
        
        flash.success("Usuario creado!");
        Application.index();
    }
}
