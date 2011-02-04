package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Feb 4, 2011
 * Time: 6:46:06 PM
 */
@Entity
public class Message extends Model {
    public String content;

    public String toString()
    {
        return content;
    }
}
