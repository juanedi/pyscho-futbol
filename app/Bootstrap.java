import models.Player;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/*
 * Copyright (c) 2012 Zauber S.A.  -- All rights reserved
 */

/**
 * TODO Descripcion de la clase. Los comentarios van en castellano.
 * 
 * 
 * @author Juan Edi
 * @since Feb 19, 2012
 */
@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Fixtures.deleteDatabase();
        Fixtures.loadModels("data.yml");
    }
    
}
