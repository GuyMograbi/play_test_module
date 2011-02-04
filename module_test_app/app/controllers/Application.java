package controllers;

import models.Message;
import notifiers.ApplicationMailer;
import play.mvc.Controller;

import java.io.*;
import java.util.Collection;

public class Application extends Controller {

    public static void index() {
        Collection<Message> msgs = Message.all().fetch();
        render(msgs);
    }

    public static void login(String username, String password) {
        if (username == null) {
            throw new RuntimeException("need username");
        }
        if (password == null) {
            throw new RuntimeException("need password");
        }
        render();
    }


    public static void dashboard()
    {
        index();
    }


    public static void sendEmail(String recipient, String message)
    {
        ApplicationMailer.sendMail(recipient, message);
        renderText("message sent successfully");
    }

      public static String convertStreamToString(InputStream is) {
          try{
        /*
         * To convert the InputStream to String we use the
         * Reader.read(char[] buffer) method. We iterate until the
         * Reader return -1 which means there's no more data to
         * read. We use the StringWriter class to produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
          }catch(Exception e){return "error while reading";}
    }

    public static void message()
    {
        System.out.println("from Application#message");
        String s = convertStreamToString(request.body);
        Message m = new Message();
        m.content = s;
        m.create();
        renderText(s);
    }
                                                
    public static void showPage( Long id )
    {
        renderText("showing page : " + id);
    }


    public static void changeSomething()
    {
        showPage( 1L );
    }

}