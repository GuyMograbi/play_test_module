import org.junit.Assert;
import org.junit.Test;
import test.controllers.TApplication;
import test.controllers.TLibrary;
import test.module.AssertExtensions;
import test.module.Charset;
import test.module.ContentType;
import test.module.TestModuleFunctionalTest;

import java.util.Collection;
import java.util.HashSet;

import static test.module.MailWrapper.getLastMailTo;
import static test.module.TestModuleController.assertRedirect;

public class ApplicationTest extends TestModuleFunctionalTest<TApplication> {

    @Test
    public void testThatIndexPageWorks() {
        controller()._index()
                .assertIsOk()
                .assertContentType(ContentType.TEXT_HTML)
                .assertCharset(Charset.UTF_8);
        System.out.println("response().toString() = " + response().toString());
        controller()._login("username","password");
        System.out.println("response().toString() = " + response().toString());

    }

    @Test
    public void shouldLoadAnotherController()
    {
        controller()._index()
                .assertIsOk();
        controller(TLibrary.class)._getMessages()
                .assertIsOk();

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
        // can also be done like this
        AssertExtensions.assertContains(getLastMailTo("play@play.com").toString(),
                "hey","man!");
    }

    @Test
    public void checkAssertExtensions()
    {
        Collection<Long> actual = new HashSet<Long>();
        Collection<Long> expected = new HashSet<Long>();
        actual.add(2L);
        actual.add(3L);
        expected.add(2L);
        expected.add(3L);
        Assert.assertEquals("collections do not match",actual,expected);
    }

    @Test
    public void testRequestWithBody()
    {
        long before = controller().countMessages();
        controller().postMessage("myMessage").assertIsOk();
        long after = controller().countMessages();
        Assert.assertEquals(after, before+1);
    }
    
}