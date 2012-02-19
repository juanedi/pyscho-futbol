package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        Player mvp = Player.find("byUsername", "mrtnslv").first();
        Player pichichi = Player.find("byUsername", "jedi").first();
        Player piedra = Player.find("byUsername", "fmolina").first();
        render(mvp, pichichi, piedra);
    }

}