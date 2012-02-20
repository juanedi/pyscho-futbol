/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */
package controllers;

import java.util.List;

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
    
    public static void postVenue(final String name, final String address) {
        Venue venue = new Venue();
        venue.name = name;
        venue.address = address;
        venue.save();
        list();
    }
}
