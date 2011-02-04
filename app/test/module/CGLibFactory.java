package test.module;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:02:07 PM
 */
public class CGLibFactory {


    public static <T> T generateController(Class<T> t) {


        Class<?> actualController = null;
        if (t.isAnnotationPresent(ControllerTest.class)) {
            ControllerTest annotation = t.getAnnotation(ControllerTest.class);
            actualController = annotation.value();
        }

        if (actualController == null) {
            actualController = tryClass("controllers." + t.getSimpleName());
        }
        // support T prefix
        if (actualController == null && t.getSimpleName().startsWith("T")) {
            actualController = tryClass("controllers." + t.getSimpleName().substring(1));

        }

        if (actualController == null) {
            throw new RuntimeException("could not understand which controller to create for : [" + t + "]");
        }
        TestModuleController interceptor = new TestModuleController(actualController);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(interceptor);


        return (T) enhancer.create();
    }

    private static <T> Class<?> tryClass(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
        }
        return null;
    }
}
