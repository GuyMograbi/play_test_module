package test.module;

import junit.framework.Assert;
import play.mvc.Http;
import play.test.FunctionalTest;

import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:11:21 PM
 */
public class TestModuleResponse extends Http.Response {

    String val;

    public TestModuleResponse(Http.Response res) {
        this.contentType = res.contentType;
        this.cookies = res.cookies;
        this.direct = res.direct;
        this.headers = res.headers;
        this.out = res.out;
        val = res.out.toString();
        this.status = res.status;
    }
   

    public String getRedirect()
    {
        return headers.get("Location").value().substring("http://localhost".length());
    }

    public TestModuleResponse assertIsOk() {
        Assert.assertEquals("Response was not ok.  " + toString(), (Object)200, this.status);
        return this;
    }

    public TestModuleResponse assertContentType(ContentType contentType) {
        return assertContentType(contentType.value);
    }

    public TestModuleResponse assertContentType(String str) {
        FunctionalTest.assertContentType(str, this);
        return this;
    }

    public TestModuleResponse assertCharset(String str)
    {
        FunctionalTest.assertCharset(str, this);
        return this;
    }

    public TestModuleResponse assertCharset(Charset charset) {
        return assertCharset(charset.value);
    }

    public Map<String, Http.Header> getHeaders()
    {
        return this.headers;
    }

    public Integer getStatus()
    {
        return this.status;
    }


    @Override
    public String toString() {
        return val;
    }

    public void assertContains(String str) {
        Assert.assertTrue(str.contains(str));
    }
}
