package test.module;

/**
 * Created by IntelliJ IDEA.
 * User: guy mograbi
 * Date: Feb 26, 2011
 * Time: 1:53:12 AM
 * To change this template use File | Settings | File Templates.
 * Allowing users to extend their own functionalities to the response
 */
public interface TestResponse {
    /**
     *
     * @param response - original response
     */
    public void initialize(TestModuleResponse response);
}
