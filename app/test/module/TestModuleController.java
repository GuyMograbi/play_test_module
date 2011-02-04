package test.module;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;
import org.junit.Assert;
import play.mvc.Http;
import play.mvc.Router;
import play.mvc.Scope;
import play.test.FunctionalTest;
import play.utils.Java;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:30:18 PM
 */
public class TestModuleController implements MethodInterceptor {
    Class<?> superclzz;
    public static TestModuleResponse lastResponse;
    private static RequestBody body;

    public static Logger logger = Logger.getLogger(TestModuleController.class);

    public static enum Flags
    {
        assertRedirect
    }

    private static FlagsSet<Flags> flags = new FlagsSet<Flags>();
    private static Scope.RouteArgs requestParams = new Scope.RouteArgs();
    private static Map<String, Http.Cookie> cookies = new HashMap<String,Http.Cookie>();



    public TestModuleController(Class<?> actualController) {
        superclzz = actualController;
    }


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().startsWith("_")) {
            Method[] methods = superclzz.getMethods();
            for (Method m : methods) {
                // looking for public static method
                String controllerMethodName = method.getName().substring(1);
                if (m.getName().equals(controllerMethodName) && m.getModifiers()  == 9 && m.getReturnType() == void.class) {

                    Http.Request req = FunctionalTest.newRequest();
                    Http.Request.current.set(req);

                    req.cookies.putAll( cookies );

                    // auto generate params from request signature
                    String[] strings = Java.parameterNames(method);
                    for (int i = 0, stringsLength = strings.length; i < stringsLength; i++) {
                        String pName = strings[i];
                        if ( objects[i] != null )
                        {
                            req.args.put(pName, objects[i]);
                        }
                    }
                    
                    Router.ActionDefinition definition = Router.reverse(superclzz.getName() + "." + method.getName().substring(1), req.args);
                    logger.info("sending request : " + definition );
                    if (flags.is(Flags.assertRedirect)) {
                        flags.remove(Flags.assertRedirect);
                        Assert.assertNotNull("No previous requests made", lastResponse);
                        Assert.assertEquals("Last response was not a redirect ( return code 302 ) , the return code was : [" + lastResponse.status + "]", new Integer(302), lastResponse.status);
                        Assert.assertEquals("Redirect address does not match new call. [redirect,new call] = [" + lastResponse.getRedirect() + "," + definition.url + "]", lastResponse.getRedirect(), definition.url);
                    }




                    if ("GET".equalsIgnoreCase(definition.method)) {
                        lastResponse = new TestModuleResponse(FunctionalTest.GET(req,definition.url));
                    } else if ("POST".equalsIgnoreCase(definition.method)) {
                        if ( body.exists() )
                        {
                                if ( body.isString() )
                                {
                                    lastResponse = new TestModuleResponse(FunctionalTest.POST(req, definition.url, body.getContentType(), body.getStr()));
                                }
                                else if ( body.isInputStream() )
                                {
                                    lastResponse = new TestModuleResponse(FunctionalTest.POST(req, definition.url, body.getContentType(), body.getIs()));
                                }
                        }
                        else
                        {
                            lastResponse = new TestModuleResponse(FunctionalTest.POST(req, definition.url));
                        }
                    }

                    requestParams = new Scope.RouteArgs();
                    if ( body != null ) // make new bodies as less as possible.
                    {
                        body = null;
                    }

                    // don't clear cookies, that's why they are there..

                    return Void.TYPE;
                }
            }
        } else {
            return methodProxy.invokeSuper(o, objects);
        }

        throw new RuntimeException("unable to send request. I have no idea what to do");
    }

    public static void clearFlags()
    {
        flags.clear();
    }

    public static Map<String, Http.Cookie> cookies()
    {
        return cookies;
    }
    
    public static Scope.RouteArgs args()
    {
        return requestParams;
    }

    public static void assertRedirect()
    {
        flags.add(Flags.assertRedirect);
    }

    public static void setRequestBody(RequestBody body)
    {
        TestModuleController.body = body;
    }
}
