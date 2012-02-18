import models.Player;

import org.junit.Test;

import play.test.UnitTest;

/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */

/**
 * Prueba persistencia de las clases de modelo
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
public class ModelTest extends UnitTest {

    @Test
    public void playerPersistenceTest() {
        Player player = new Player();
        player.username = "jedi";
        player.password = "jedi";
        player.firstName = "Juan";
        player.lastName = "Edi";
        
        player.save();
        
        Player retrieved = Player.find("byUsername", "jedi").first();
        
        assertNotNull(retrieved);
        assertEquals("Juan", retrieved.firstName);
    }
    
}
