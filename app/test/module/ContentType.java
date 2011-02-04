package test.module;

/**
* Created by IntelliJ IDEA.
* User: User
* Date: Jan 29, 2011
* Time: 3:14:39 PM
*/
public enum ContentType
{
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_HTML("text/html");

    String value;
    ContentType(String s) {
        value = s; 
    }

    
}
