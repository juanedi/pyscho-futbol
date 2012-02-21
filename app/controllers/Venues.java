/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.List;

import play.data.validation.Required;

import models.Venue;

/**
 * Canchas
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
public class Venues extends SecureController {

    public static void list() {
        List<Venue> venues = Venue.all().fetch();
        render(venues);
    }
    
    public static void postVenue(@Required final String name,@Required  final String address) {
        if (validation.hasErrors()) {
            flash.error("Todos los campos son requeridos");
            list();
        }
        Venue venue = new Venue();
        venue.name = name;
        venue.address = address;
        venue.save();
        list();
    }
}
