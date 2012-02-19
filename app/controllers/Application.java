package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import dto.StrikerDTO;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        StrikerDTO striker = Player.allTimeStriker();
        Player mvp = Player.find("byUsername", "mrtnslv").first();
        Player piedra = Player.find("byUsername", "fmolina").first();
        render(mvp, striker, piedra);
    }

}