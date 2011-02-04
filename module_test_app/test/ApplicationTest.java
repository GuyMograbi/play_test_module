import org.junit.Assert;
import org.junit.Test;
import test.controllers.TApplication;
import test.module.Charset;
import test.module.ContentType;
import test.module.TestModuleFunctionalTest;

import static test.module.MailWrapper.getLastMailTo;
import static test.module.TestModuleController.assertRedirect;

public class ApplicationTest extends TestModuleFunctionalTest<TApplication> {

    @Test
    public void testThatIndexPageWorks() {
        controller()._index();
        response()
                .assertIsOk()
                .assertContentType(ContentType.TEXT_HTML)
                .assertCharset(Charset.UTF_8);

        System.out.println("response().toString() = " + response().toString());
        controller()._login("username","password");
        System.out.println("response().toString() = " + response().toString());

    }

    @Test
    public void testRedirect()
    {
        controller()._dashboard();
        assertRedirect();
        controller()._index();
        controller()._changeSomething();
        assertRedirect();
        controller()._showPage(1L);
    }

    @Test
    public void testMail()
    {
        controller()._sendEmail("play@play.com","hey man!");
        getLastMailTo("play@play.com").assertContains("hey man!");
    }

    @Test
    public void testRequestWithBody()
    {
        long before = controller().countMessages();
        controller().postMessage("myMessage");
        response().assertIsOk();
        long after = controller().countMessages();
        Assert.assertEquals(after, before+1);
    }
    
}