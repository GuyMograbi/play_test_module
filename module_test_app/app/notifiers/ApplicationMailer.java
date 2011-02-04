package notifiers;

import play.mvc.Mailer;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Feb 4, 2011
 * Time: 5:59:34 PM
 */
public class ApplicationMailer extends Mailer{


    public static void sendMail(String recipient, String str)
    {
        setFrom("application@play.com");
        setSubject("hello world!");
        addRecipient(recipient);
        send(str);
    }

}
