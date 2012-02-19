import models.Player;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */

/**
 * Importa data de prueba
 * 
 * 
 * @author Juan Edi
 * @since Feb 18, 2012
 */
@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Fixtures.deleteDatabase();
        Fixtures.load("data.yml");
//        if (Player.count() == 0) {
//        }
        
    }
    
}
