package controllers;

import models.Message;
import play.mvc.Controller;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: guy mograbi
 * Date: Mar 11, 2011
 * Time: 8:38:15 AM
 */
public class Library extends Controller {


    public static void getMessages()
    {
        Collection<Message> msgs = Message.all().fetch();
        render(msgs);
    }

}
