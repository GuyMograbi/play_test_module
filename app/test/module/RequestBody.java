package test.module;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Feb 4, 2011
 * Time: 6:25:29 PM
 */
public class RequestBody {
    private final InputStream is;
    private final String str;
    private final String contentType;

    public RequestBody(InputStream is, String contentType) {
        this.is = is;
        this.str = null;
        this.contentType = contentType;
    }

    public RequestBody(String str) {
        this(str, ContentType.MULTIPART_FORM_DATA);

    }

    public RequestBody(InputStream is)
    {
        this(is, ContentType.MULTIPART_FORM_DATA);
    }

    public RequestBody(String str, ContentType type) {
        this(str, type.value);

    }

    public RequestBody(InputStream is, ContentType type) {
        this(is, type.value);
    }

    public RequestBody(String str, String contentType) {
        this.str = str;
        this.is = null;
        this.contentType = contentType;
    }

    public boolean isEmpty() {
        return str == null && is == null;
    }

    public boolean isInputStream() {
        return is != null;
    }

    public boolean isString() {
        return str != null;
    }

    public boolean exists() {
        return !isEmpty();
    }

    public InputStream getIs() {
        return is;
    }

    public String getStr() {
        return str;
    }

    public String getContentType() {
        return contentType;
    }


}
