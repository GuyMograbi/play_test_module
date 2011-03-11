package test.module;

import junit.framework.Assert;

import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: guy mograbi
 * Date: Feb 26, 2011
 * Time: 2:31:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssertExtensions {


    /**
     * asserts that "expected" is a substring in actual
     * @param actual - the actual string received.
     * @param expected - the expected substrings to be contained in actual
     */
    public static void assertContains( String actual, String ... expected )
    {
         assertContainsWithComment("substring [{0}] not found in [{1}]", actual, expected);
    }


    public static void assertContainsWithComment(String comment, String actual, String ... expected )
    {
        for (String exp : expected) {

            Assert.assertTrue(MessageFormat.format(comment,actual,exp), actual.contains( exp ));
        }
    }

}
