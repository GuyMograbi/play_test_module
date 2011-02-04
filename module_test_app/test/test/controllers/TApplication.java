package test.controllers;

import controllers.Application;
import models.Message;
import test.module.RequestBody;

import static test.module.TestModuleController.setRequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:07:31 PM
 */
public class TApplication extends Application {

    public void _index() {
    }

    public void _login(String username, String password) {
    }

    public void _sendEmail(String recipient, String message) {

    }

    public void _message() {

    }

    public Message getMessageByContent(String message) {
        return Message.find("content like ?", message).first();
    }

    public void removeAllMessages(String message) {
        Message.delete("content like ?", message);
    }

    public long countMessages() {
        return Message.count();
    }


    public void postMessage(String message) {
        setRequestBody(new RequestBody(message));
        _message();
    }

    public void _changeSomething() {
    }

    public void _showPage(Long id) {
    }

    public void _dashboard() {
    }
}
