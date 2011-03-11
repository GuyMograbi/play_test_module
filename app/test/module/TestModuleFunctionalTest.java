package test.module;

import org.junit.Ignore;
import play.mvc.Controller;
import play.test.FunctionalTest;

import java.lang.reflect.ParameterizedType;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:06:59 PM
 */
@Ignore 
public class TestModuleFunctionalTest<T extends Controller> extends FunctionalTest {

    T controller;

    public TestModuleFunctionalTest() {
       Class<T> t =  ((Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]);
        controller = CGLibFactory.generateController(t);
    }

    public <V> V controller(Class<V> clzz)
    {
        return CGLibFactory.generateController(clzz);
    }

    public T controller()
    {
       return controller; 
    }

    public TestModuleResponse response(){
        return TestModuleController.lastResponse;
    }
}
