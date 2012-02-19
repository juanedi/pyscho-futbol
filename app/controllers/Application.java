package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import dto.StrikerDTO;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        Match pendingMatch = Match.nextMatch();
        StrikerDTO striker = Player.allTimeStriker();
        PlayerStat mvp = PlayerStat.mvp();
        PlayerStat lvp = PlayerStat.lvp();
        render(pendingMatch, mvp, striker, lvp);
    }

}