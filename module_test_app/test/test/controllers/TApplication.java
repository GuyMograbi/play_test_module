package test.controllers;

import controllers.Application;
import models.Message;
import test.module.RequestBody;
import test.module.TestModuleResponse;

import static test.module.TestModuleController.setRequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Jan 29, 2011
 * Time: 3:07:31 PM
 */
public class TApplication extends Application {

    public TestModuleResponse _index() {
        return null;
    }

    public void _login(String username, String password) {
    }

    public void _sendEmail(String recipient, String message) {

    }

    public TestModuleResponse _message() {
            return null;
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


    public TestModuleResponse postMessage(String message) {
        setRequestBody(new RequestBody(message));
        return _message();
    }

    public void _changeSomething() {
    }

    public void _showPage(Long id) {
    }

    public void _dashboard() {
    }
}
