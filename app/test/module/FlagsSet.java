package test.module;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Feb 4, 2011
 * Time: 5:24:55 PM
 */
public class FlagsSet<T extends Enum> extends HashSet
{
    public boolean is(T e)
    {
        return contains(e);
    }
}
