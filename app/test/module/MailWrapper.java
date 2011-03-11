package test.module;

import org.junit.Assert;
import play.libs.Mail;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Feb 4, 2011
 * Time: 6:06:26 PM
 */
public class MailWrapper {

    private String message;
    public MailWrapper(String message) {
        this.message = message;
    }

    public static MailWrapper getLastMailTo(String recipient)
    {
        return new MailWrapper(Mail.Mock.getLastMessageReceivedBy(recipient));
    }

    @Override
    public String toString() {
        return message;
    }

    public void assertContains(String substring)
    {
        Assert.assertNotNull("The last email recieved is null",message);
        Assert.assertTrue("Message should've contained [" + substring + "] but does not .. this is the message : [" + message+ "]", message.contains(substring));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
